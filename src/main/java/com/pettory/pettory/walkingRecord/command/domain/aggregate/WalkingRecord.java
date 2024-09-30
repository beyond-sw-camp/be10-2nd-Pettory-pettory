package com.pettory.pettory.walkingRecord.command.domain.aggregate;

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

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "walking_record")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE walking_record SET walking_record_state = 'DELETE', walking_record_delete_datetime = NOW() WHERE walking_record_id = ? AND walking_record_state != 'DELETE'")
public class WalkingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walkingRecordId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    private LocalDate walkingRecordDate; // 산책 기록 날짜
    private Long walkingRecordDuration; // 산책 시간 (분 단위)
    private Long walkingRecordPoopCount; // 배변 횟수
    private String walkingRecordWaterAmount; // 물 섭취량
    private String walkingRecordMemo; // 메모

    @CreatedDate
    private LocalDateTime walkingRecordInsertDatetime;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime walkingRecordUpdateDatetime;
    private LocalDateTime walkingRecordDeleteDatetime;

    @Enumerated(EnumType.STRING)
    private WalkingRecordState walkingRecordState = WalkingRecordState.ACTIVE;

    public static WalkingRecord create(User user, Pet pet, LocalDate date, Long duration, Long poopCount, String waterAmount, String memo) {
        WalkingRecord walkingRecord = new WalkingRecord();
        walkingRecord.user = user;
        walkingRecord.pet = pet;
        walkingRecord.walkingRecordDate = date;
        walkingRecord.walkingRecordDuration = duration;
        walkingRecord.walkingRecordPoopCount = poopCount;
        walkingRecord.walkingRecordWaterAmount = waterAmount;
        walkingRecord.walkingRecordMemo = memo;
        walkingRecord.walkingRecordState = WalkingRecordState.ACTIVE;
        return walkingRecord;
    }

    public void update(LocalDate walkingRecordDate, Long walkingRecordDuration, Long walkingRecordPoopCount, String walkingRecordWaterAmount, String walkingRecordMemo) {
        if (walkingRecordDate != null) this.walkingRecordDate = walkingRecordDate;
        if (walkingRecordDuration != null) this.walkingRecordDuration = walkingRecordDuration;
        if (walkingRecordPoopCount != null) this.walkingRecordPoopCount = walkingRecordPoopCount;
        if (walkingRecordWaterAmount != null && !walkingRecordWaterAmount.trim().isEmpty())
            this.walkingRecordWaterAmount = walkingRecordWaterAmount;
        if (walkingRecordMemo != null && !walkingRecordMemo.trim().isEmpty())
            this.walkingRecordMemo = walkingRecordMemo;
    }

}
