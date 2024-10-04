package com.pettory.userserver.family.command.domain.repository;

import com.pettory.userserver.family.command.domain.aggregate.Invitation;
import com.pettory.userserver.family.command.domain.aggregate.InvitationState;

import java.util.Optional;

public interface InvitationRepository {
    // 초대 테이블에 저장하는 메소드
    Invitation save(Invitation invitation);

    Optional<Invitation> findById(Long invitationId);

    boolean existsByInvitationSendUserIdAndInvitationReceiveUserIdAndInvitationState(Long userId, Long userId1, InvitationState invitationState);

    Optional<Invitation> findByInvitationIdAndInvitationState(Long invitationId, InvitationState invitationState);
}
