package com.pettory.mainserver.walkinggroup.command.domain.aggregate;

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
@Table(name = "walking_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE walking_group SET walking_group_state = 'DELETE', walking_group_delete_datetime = now() WHERE walking_group_id = ?")
public class WalkingGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int walkingGroupId;
    private String walkingGroupName;
    private String walkingGroupInfo;
    private int walkingGroupMaximumCount;
    @Enumerated(EnumType.STRING)
    private WalkingGroupState walkingGroupState = WalkingGroupState.APPLICATION;
    @CreatedDate
    private LocalDateTime walkingGroupInsertDatetime;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime walkingGroupUpdateDatetime;
    private LocalDateTime walkingGroupDeleteDatetime;
    private int walkingGroupOwner;

    public WalkingGroup(String walkingGroupName, String walkingGroupInfo, int walkingGroupMaximumCount, int walkingGroupOwner) {
        this.walkingGroupName = walkingGroupName;
        this.walkingGroupInfo = walkingGroupInfo;
        this.walkingGroupMaximumCount = walkingGroupMaximumCount;
        this.walkingGroupOwner = walkingGroupOwner;
    }

    public static WalkingGroup create(String walkingGroupName, String walkingGroupInfo, int walkingGroupMaximumCount, int walkingGroupOwner) {
        return new WalkingGroup(walkingGroupName, walkingGroupInfo, walkingGroupMaximumCount,walkingGroupOwner);
    }

    public void updateWalkingGroupDetails(String walkingGroupName, String walkingGroupInfo, int walkingGroupMaximumCount, WalkingGroupState walkingGroupState) {
        this.walkingGroupName = walkingGroupName;
        this.walkingGroupInfo = walkingGroupInfo;
        this.walkingGroupMaximumCount = walkingGroupMaximumCount;
        this.walkingGroupState = walkingGroupState;
    }
}
