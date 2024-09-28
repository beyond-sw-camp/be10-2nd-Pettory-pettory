package com.pettory.pettory.family.query.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.family.query.dto.ReceivedInvitationResponse;
import com.pettory.pettory.family.query.service.InvitationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("invitation/show")
public class InvitationQueryController {

    private final InvitationQueryService invitationQueryService;

    // 받은 초대 조회
    @GetMapping("/invitations")
    public ResponseEntity<CommonResponseDTO> showReceivedInvitation(@RequestParam Long receiverId) {

        List<ReceivedInvitationResponse> invitations = invitationQueryService.getReceivedInvitation(receiverId);

        CommonResponseDTO response = new CommonResponseDTO(HttpStatus.OK.value(), "초대 조회 성공", invitations);
        return ResponseEntity.ok(response);
    }
}
