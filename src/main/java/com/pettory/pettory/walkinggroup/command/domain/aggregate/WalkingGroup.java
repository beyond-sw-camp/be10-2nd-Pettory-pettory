package com.pettory.pettory.walkinggroup.command.domain.aggregate;

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
@SQLDelete(sql = "UPDATE walking_group SET walking_group_state = 'DELETE' WHERE walking_group_id = ?")
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
    @LastModifiedDate
    private LocalDateTime walkingGroupUpdateDatetime;
    private LocalDateTime walkingGroupDeleteDatetime;

    public WalkingGroup(String walkingGroupName, String walkingGroupInfo, int walkingGroupMaximumCount) {
        this.walkingGroupName = walkingGroupName;
        this.walkingGroupInfo = walkingGroupInfo;
        this.walkingGroupMaximumCount = walkingGroupMaximumCount;
    }

    public static WalkingGroup create(String walkingGroupName, String walkingGroupInfo, int walkingGroupMaximumCount) {
        return new WalkingGroup(walkingGroupName, walkingGroupInfo, walkingGroupMaximumCount);
    }

    public void updateWalkingGroupDetails(String walkingGroupName, String walkingGroupInfo, int walkingGroupMaximumCount, WalkingGroupState walkingGroupState) {
        this.walkingGroupName = walkingGroupName;
        this.walkingGroupInfo = walkingGroupInfo;
        this.walkingGroupMaximumCount = walkingGroupMaximumCount;
        this.walkingGroupState = walkingGroupState;
    }
}
