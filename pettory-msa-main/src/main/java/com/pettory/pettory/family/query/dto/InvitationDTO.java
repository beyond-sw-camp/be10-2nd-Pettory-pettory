package com.pettory.pettory.family.query.dto;

import com.pettory.pettory.family.command.domain.aggregate.InvitationState;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InvitationDTO {
    private Long invitationId;  // 초대id
    private Long invitationSendUserId;  // 초대하는회원id
    private Long invitationReceiveUserId;   // 초대받는회원id
    private Long familyId;  // 가족id
    private InvitationState invitationState = InvitationState.PENDING;    // 초대상태
    private LocalDateTime invitationCreatedAt;  // 초대생성일시
    private LocalDateTime invitationUpdatedAt;  // 초대변경일시

}
