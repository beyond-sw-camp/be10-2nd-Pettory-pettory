package com.pettory.pettory.family.command.domain.repository;

import com.pettory.pettory.family.command.domain.aggregate.Invitation;
import com.pettory.pettory.family.command.domain.aggregate.InvitationState;

import java.util.Optional;

public interface InvitationRepository {
    // 초대 테이블에 저장하는 메소드
    Invitation save(Invitation invitation);

    Optional<Invitation> findById(Long invitationId);

    boolean existsByInvitationSendUserIdAndInvitationReceiveUserIdAndInvitationState(Long userId, Long userId1, InvitationState invitationState);
    // 초대를 보낸 회원의 id와 초대를 받은 회원의 id가 일치하는 레코드가 있는지 확인하는 메소드
}
