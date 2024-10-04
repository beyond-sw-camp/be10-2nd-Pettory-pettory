package com.pettory.pettory.walkingGroupApplication.command.domain.aggregate;

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
@Table(name ="register_walking_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE register_walking_group SET register_walking_group_delete_datetime = now() WHERE register_walking_group_id = ?")
public class RegisterWalkingGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int registerWalkingGroupId;
    private int walkingGroupId;
    @Enumerated(value = EnumType.STRING)
    private RegisterWalkingGroupState registerWalkingGroupState = RegisterWalkingGroupState.ACTIVE;
    private int userId;
    @CreatedDate
    private LocalDateTime registerWalkingGroupInsertDatetime;
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime registerWalkingGroupUpdateDatetime;
    private LocalDateTime registerWalkingGroupDeleteDatetime;

    public static RegisterWalkingGroup create(int walkingGroupId, int userId) {
        return new RegisterWalkingGroup(walkingGroupId, userId);
    }

    public RegisterWalkingGroup(int walkingGroupId, int userId) {
        this.walkingGroupId = walkingGroupId;
        this.userId = userId;
    }

    public void updateRegisterWalkingGroupDetails(RegisterWalkingGroupState registerWalkingGroupState) {
        this.registerWalkingGroupState = registerWalkingGroupState;
    }
}
