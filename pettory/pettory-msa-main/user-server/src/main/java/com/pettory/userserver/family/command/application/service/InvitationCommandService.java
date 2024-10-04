package com.pettory.userserver.family.command.application.service;

import com.pettory.userserver.exception.*;
import com.pettory.userserver.family.command.application.dto.InviteToFamilyRequest;
import com.pettory.userserver.family.command.domain.aggregate.Family;
import com.pettory.userserver.family.command.domain.aggregate.FamilyState;
import com.pettory.userserver.family.command.domain.aggregate.Invitation;
import com.pettory.userserver.family.command.domain.aggregate.InvitationState;
import com.pettory.userserver.family.command.domain.repository.FamilyRepository;
import com.pettory.userserver.family.command.domain.repository.InvitationRepository;
import com.pettory.userserver.security.util.UserSecurity;
import com.pettory.userserver.user.command.domain.aggregate.User;
import com.pettory.userserver.user.command.domain.repository.UserRepository;
import com.pettory.userserver.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvitationCommandService {

    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;
    private final FamilyRepository familyRepository;
    private final UserMapper userMapper;

    // 다른 사용자를 가족으로 초대
    @Transactional
    public void inviteUserToFamily(String invitationSenderEmail, InviteToFamilyRequest inviteToFamilyRequest) {

        UserSecurity.validateCurrentUser(invitationSenderEmail);

        // 1. 초대하는 회원 조회
        User sender = userRepository.findByUserEmail(invitationSenderEmail)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        // 2. 초대받는 회원 조회
        User receiver = userRepository.findByUserEmail(inviteToFamilyRequest.getReceivedUserEmail())
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        // 3. 초대하는 회원과 초대받는 회원 비교
        if (sender.getUserId().equals(receiver.getUserId())) {
            throw new SelfException("자신에게 초대를 보낼 수 없습니다.");
        }

        // 4. 초대 중복 확인(PENDING 상태인 보낸 초대)
        if (invitationRepository.existsByInvitationSendUserIdAndInvitationReceiveUserIdAndInvitationState(
                sender.getUserId(), receiver.getUserId(), InvitationState.PENDING
        )) {
            throw new AlreadySentInvitationException("이미 초대를 보냈습니다.");
        }

        // 5. 초대받는 회원이 가족에 속해 있는지 확인
        Family receiverFamily = receiver.getFamily();
        if (receiverFamily != null) {
            // 가족 구성원이 한 명인 가족에 속한 경우
            if (receiverFamily.getFamilyNumber() <= 1) {
                // 가족 상태를 DELETED로 변경
                receiverFamily.updateFamilyStateAsDeleted();
                // 초대받는 회원의 가족id 삭제
                receiver.updateFamilyIdAsNull();
            } else {
                throw new AlreadyInFamilyException("이미 가족에 속해있습니다.");
            }
        }

        // 6. 초대하는 회원이 가족이 있는지 확인
        Family senderFamily = sender.getFamily();

        if (senderFamily == null) {
            // 초대하는 회원의 가족이 없으면 가족 생성
            senderFamily = Family.createFamily(sender.getUserName() + "님의 가족", FamilyState.ACTIVE, sender.getUserId());
            familyRepository.save(senderFamily);

            // 초대하는 회원을 생성한 가족에 추가
            sender.joinFamily(senderFamily);
            userRepository.save(sender);
        } else {
            // 가족이 있으면 가족의 주인인지 확인
            if (!senderFamily.isOwner(sender.getUserId())) {
                throw new UnauthorizedException("가족의 주인만 초대를 보낼 수 있습니다.");
            }
        }

        // 7. 초대 생성
        Invitation invitation = Invitation.createInvitation(sender.getUserId(), receiver.getUserId(), senderFamily);
        invitationRepository.save(invitation);
    }

    // 가족 초대를 거절
    @Transactional
    public void rejectInvitation(Long invitationId, String userEmail) {

        // 로그인한 회원 id 조회
        Long userId = userMapper.findUserIdByEmail(userEmail).getUserId();

        // 초대 조회
        Invitation invitation = invitationRepository.findByInvitationIdAndInvitationState(invitationId, InvitationState.PENDING)
                .orElseThrow(() -> new NotFoundException("초대를 찾을 수 없습니다."));

        if (invitation.getInvitationReceiveUserId() != userId) {
            throw new UnauthorizedException("해당 초대에 접근할 권한이 없습니다.");
        }

        // 초대 상태를 REJECTED 로 변경
        invitation.changeInvitationStateRejected();

    }

    // 가족 초대 수락
    @Transactional
    public void acceptInvitation(Long invitationId, String userEmail) {

        // 로그인한 회원 id 조회
        Long userId = userMapper.findUserIdByEmail(userEmail).getUserId();

        // 초대 조회
        Invitation invitation = invitationRepository.findByInvitationIdAndInvitationState(invitationId, InvitationState.PENDING)
                .orElseThrow(() -> new NotFoundException("초대를 찾을 수 없습니다."));

        if (invitation.getInvitationReceiveUserId() != userId) {
            throw new UnauthorizedException("해당 초대에 접근할 권한이 없습니다.");
        }

        if (invitation.getInvitationState() == InvitationState.ACCEPTED) {
            throw new IllegalArgumentException("이미 수락된 초대입니다.");
        }

        // 초대 상태를 ACCEPTED 로 변경
        invitation.changeInvitationStateAccepted();
        invitationRepository.save(invitation);

        User receiver = userRepository.findById(invitation.getInvitationReceiveUserId())
                .orElseThrow(() -> new NotFoundException("회원이 존재하지 않습니다."));

        Family family = familyRepository.findById(invitation.getFamily().getFamilyId())
                .orElseThrow(() -> new NotFoundException("가족이 존재하지 않습니다."));

        receiver.joinFamily(family);
    }

}
