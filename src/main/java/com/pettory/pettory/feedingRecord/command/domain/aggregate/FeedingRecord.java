package com.pettory.pettory.feedingRecord.command.domain.aggregate;

import com.pettory.pettory.family.command.domain.aggregate.FamilyState;
import com.pettory.pettory.pet.command.domain.aggregate.Pet;
import com.pettory.pettory.user.command.domain.aggregate.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "feeding_record")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE feeding_record SET feeding_record_state = 'DELETE', feeding_record_delete_datetime = NOW() WHERE feeding_record_id = ? AND feeding_record_state != 'DELETE'")
public class FeedingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedingRecordId;
    @Enumerated(EnumType.STRING)
    private FeedingRecordFeedingType feedingRecordFeedingType;
    private String feedingRecordName;
    private String feedingRecordType;
    private String feedingRecordAmount;
    private String feedingRecordMemo;
    private LocalDateTime feedingRecordUserInsertDatetime;

    @CreatedDate
    private LocalDateTime feedingRecordInsertDatetime;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime feedingRecordUpdateDatetime;
    private LocalDateTime feedingRecordDeleteDatetime;

    @Enumerated(EnumType.STRING)
    private FeedingRecordState feedingRecordState = FeedingRecordState.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    public static FeedingRecord create(
            User user, Pet pet, FeedingRecordFeedingType feedingRecordFeedingType, String feedingRecordName, String feedingRecordType, String feedingRecordAmount, String feedingRecordMemo, LocalDateTime feedingRecordUserInsertDatetime
    ) {
        FeedingRecord feedingRecord = new FeedingRecord();
        feedingRecord.user = user;
        feedingRecord.pet = pet;
        feedingRecord.feedingRecordFeedingType = feedingRecordFeedingType;
        feedingRecord.feedingRecordName = feedingRecordName;
        feedingRecord.feedingRecordType = feedingRecordType;
        feedingRecord.feedingRecordAmount = feedingRecordAmount;
        feedingRecord.feedingRecordMemo = feedingRecordMemo;
        if (feedingRecordUserInsertDatetime == null) {
            feedingRecord.feedingRecordUserInsertDatetime = LocalDateTime.now();
        } else {
            feedingRecord.feedingRecordUserInsertDatetime = feedingRecordUserInsertDatetime;
        }
        return feedingRecord;
    }

    public void update(LocalDateTime feedingRecordUserInsertDatetime, FeedingRecordFeedingType feedingRecordFeedingType, String feedingRecordName, String feedingRecordAmount, String feedingRecordType, String feedingRecordMemo) {
        if(feedingRecordUserInsertDatetime != null) this.feedingRecordUserInsertDatetime = feedingRecordUserInsertDatetime;
        if (feedingRecordFeedingType != null) this.feedingRecordFeedingType = feedingRecordFeedingType;
        if (feedingRecordName != null && !feedingRecordName.trim().isEmpty())
            this.feedingRecordName = feedingRecordName;
        if (feedingRecordAmount != null && !feedingRecordAmount.trim().isEmpty())
            this.feedingRecordAmount = feedingRecordAmount;
        if (feedingRecordType != null && !feedingRecordType.trim().isEmpty())
            this.feedingRecordType = feedingRecordType;
        if (feedingRecordMemo != null && !feedingRecordMemo.trim().isEmpty())
            this.feedingRecordMemo = feedingRecordMemo;
    }

//    // feeding_record 삭제 시간을 update 하는 메소드
//    public void updateFeedingRecordDeleteDatetime() {
//        this.feedingRecordDeleteDatetime = LocalDateTime.now();
//    }

}
