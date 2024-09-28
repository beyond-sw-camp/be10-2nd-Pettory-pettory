package com.pettory.pettory.family.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "invitation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invitationId;  // 초대id

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    private Long invitationSendUserId;  // 초대하는회원id
    private Long invitationReceiveUserId;   // 초대받는회원id
//    private Long familyId;  // 가족id

    @Enumerated(EnumType.STRING)
    private InvitationState invitationState = InvitationState.PENDING;    // 초대상태

    @CreatedDate
    private LocalDateTime invitationCreatedAt;  // 초대생성일시
    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime invitationUpdatedAt;  // 초대변경일시

    public Invitation(Long invitationSendUserId, Long invitationReceiveUserId, Family family) {
        this.invitationSendUserId = invitationSendUserId;
        this.invitationReceiveUserId = invitationReceiveUserId;
        this.family = family;
    }

    // 초대를 생성하는 메소드
    public static Invitation createInvitation(Long invitationSendUserId, Long invitationReceiveUserId, Family family) {
        return new Invitation(invitationSendUserId, invitationReceiveUserId, family);
    }

    public void acceptInvitation() {
        this.invitationState = InvitationState.ACCEPTED;
    }

    public void rejectInvitation() {
        this.invitationState = InvitationState.REJECTED;
        this.invitationUpdatedAt = LocalDateTime.now();
    }
}
