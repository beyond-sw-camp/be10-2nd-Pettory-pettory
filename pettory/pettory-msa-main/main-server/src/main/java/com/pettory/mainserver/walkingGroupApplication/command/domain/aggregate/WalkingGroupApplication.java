package com.pettory.mainserver.walkingGroupApplication.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "walking_group_application")
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE walking_group_application SET walking_group_approval_state = 'DELETE', walking_group_application_delete_datetime = NOW() WHERE walking_group_application_id = ?")
public class WalkingGroupApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int walkingGroupApplicationId;
    @Enumerated(EnumType.STRING)
    private WalkingGroupApprovalState walkingGroupApprovalState = WalkingGroupApprovalState.PENDING;
    @CreatedDate
    private LocalDateTime walkingGroupApplicationInsertDatetime;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime walkingGroupApprovalUpdateDatetime;
    private LocalDateTime walkingGroupApplicationDeleteDatetime;
    private int walkingGroupId;
    private int userId;


    private WalkingGroupApplication(int walkingGroupId, int userId) {
        this.walkingGroupId = walkingGroupId;
        this.userId = userId;
    }

    public static WalkingGroupApplication create(int walkingGroupId, int userId) {
        return new WalkingGroupApplication(walkingGroupId, userId);
    }

    public void updateWalkingGroupApplicationDetails(WalkingGroupApprovalState walkingGroupApprovalState) {
        this.walkingGroupApprovalState = walkingGroupApprovalState;
    }

}
