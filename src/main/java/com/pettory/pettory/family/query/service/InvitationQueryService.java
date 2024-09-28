package com.pettory.pettory.family.query.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.family.query.dto.InvitationDTO;
import com.pettory.pettory.family.query.dto.ReceivedInvitationResponse;
import com.pettory.pettory.family.query.mapper.InvitationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvitationQueryService {

    private final InvitationMapper invitationMapper;

    // 받은 초대 조회
    @Transactional(readOnly = true)
    public List<ReceivedInvitationResponse> getReceivedInvitation(Long receiverId) {

        List<ReceivedInvitationResponse> invitations = invitationMapper.selectReceivedInvitations(receiverId);

        if(invitations.isEmpty()) {
            throw new NotFoundException("받은 초대가 없습니다.");
        }
        return invitations;

    }
}
