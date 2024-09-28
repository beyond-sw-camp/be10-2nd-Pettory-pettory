package com.pettory.pettory.family.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.family.command.application.dto.InviteToFamilyRequest;
import com.pettory.pettory.family.command.application.dto.KickMemberRequest;
import com.pettory.pettory.family.command.application.service.InvitationCommandService;
import com.pettory.pettory.security.util.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invitations")
public class InvitationCommandController {

    private final InvitationCommandService invitationCommandService;

    // 다른 회원을 가족으로 초대
    @PostMapping
    public ResponseEntity<CommonResponseDTO> inviteToFamily(@RequestBody InviteToFamilyRequest inviteToFamilyRequest) {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        invitationCommandService.inviteUserToFamily(currentUserEmail, inviteToFamilyRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "초대 전송 성공", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);

    }

    // 가족 초대 거절
    @PutMapping("/{invitationId}/rejection")
    public ResponseEntity<CommonResponseDTO> rejectInvitation(@PathVariable Long invitationId) {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        invitationCommandService.rejectInvitation(invitationId, currentUserEmail);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "초대 거절 성공", invitationId);
        return ResponseEntity.ok(successResponse);
    }

    // 가족 초대 수락
    @PutMapping("/{invitationId}/acceptance")
    public ResponseEntity<CommonResponseDTO> acceptInvitation(@PathVariable Long invitationId) {

        String currentUseremail = UserSecurity.getCurrentUserEmail();

        invitationCommandService.acceptInvitation(invitationId, currentUseremail);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "초대 수락 성공", invitationId);
        return ResponseEntity.ok(successResponse);
    }


}
