package com.pettory.mainserver.walkingGroupRecord.command.domain.aggregate;

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
@Table(name = "walking_group_record")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE walking_group_record " +
        "SET walking_group_record_state = 'DELETE', walking_group_record_delete_datetime = now() " +
        "WHERE walking_group_record_id = ?")
public class WalkingGroupRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int walkingGroupRecordId;
    private LocalDate walkingGroupDate;
    private int walkingGroupRecordDuration;
    private String walkingGroupRouteStart;
    private String walkingGroupRouteEnd;
    @CreatedDate
    private LocalDateTime walkingGroupRecordInsertDatetime;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime walkingGroupRecordUpdateDatetime;
    private LocalDateTime walkingGroupRecordDeleteDatetime;
    @Enumerated(EnumType.STRING)
    private WalkingGroupRecordState walkingGroupRecordState = WalkingGroupRecordState.COMPLETE;
    private int walkingGroupId;

    private WalkingGroupRecord(
            LocalDate walkingGroupDate,
            int walkingGroupRecordDuration,
            String walkingGroupRouteStart,
            String walkingGroupRouteEnd,
            int walkingGroupId
            ) {
        this.walkingGroupDate = walkingGroupDate;
        this.walkingGroupRecordDuration = walkingGroupRecordDuration;
        this.walkingGroupRouteStart = walkingGroupRouteStart;
        this.walkingGroupRouteEnd = walkingGroupRouteEnd;
        this.walkingGroupId = walkingGroupId;
    }

    public static WalkingGroupRecord create(
            LocalDate walkingGroupDate,
            int walkingGroupRecordDuration,
            String walkingGroupRouteStart,
            String walkingGroupRouteEnd,
            int walkingGroupId
    ) {
        return new WalkingGroupRecord(
                walkingGroupDate,
                walkingGroupRecordDuration,
                walkingGroupRouteStart,
                walkingGroupRouteEnd,
                walkingGroupId
        );
    }

    public void updateWalkingGroupRecordDetails(
            LocalDate walkingGroupDate,
            int walkingGroupRecordDuration,
            String walkingGroupRouteStart,
            String walkingGroupRouteEnd,
            WalkingGroupRecordState walkingGroupRecordState
    ) {
        this.walkingGroupDate = walkingGroupDate;
        this.walkingGroupRecordDuration = walkingGroupRecordDuration;
        this.walkingGroupRouteStart = walkingGroupRouteStart;
        this.walkingGroupRouteEnd = walkingGroupRouteEnd;
        this.walkingGroupRecordState = walkingGroupRecordState;
    }

}
