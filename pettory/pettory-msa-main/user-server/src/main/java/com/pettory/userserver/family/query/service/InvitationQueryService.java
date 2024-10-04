package com.pettory.userserver.family.query.service;

import com.pettory.userserver.exception.EmptyResultException;
import com.pettory.userserver.family.query.dto.ReceivedInvitationResponse;
import com.pettory.userserver.family.query.mapper.InvitationMapper;
import com.pettory.userserver.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvitationQueryService {

    private final InvitationMapper invitationMapper;
    private final UserMapper userMapper;

    // 받은 초대 조회
    @Transactional(readOnly = true)
    public List<ReceivedInvitationResponse> getReceivedInvitation(String userEmail) {

        Long userId = userMapper.findUserIdByEmail(userEmail).getUserId();

        List<ReceivedInvitationResponse> invitations = invitationMapper.selectReceivedInvitations(userId);

        if(invitations.isEmpty()) {
            throw new EmptyResultException("받은 초대가 없습니다.");
        }
        return invitations;

    }
}
