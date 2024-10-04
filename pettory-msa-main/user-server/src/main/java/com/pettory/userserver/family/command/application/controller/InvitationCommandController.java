package com.pettory.userserver.family.command.application.controller;

import com.pettory.userserver.common.CommonResponseDTO;
import com.pettory.userserver.family.command.application.dto.InviteToFamilyRequest;
import com.pettory.userserver.family.command.application.service.InvitationCommandService;
import com.pettory.userserver.security.util.UserSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pettory 가족 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/invitations")
public class InvitationCommandController {

    private final InvitationCommandService invitationCommandService;

    @Operation(summary = "회원을 가족으로 초대", description = "다른 회원을 자신의 가족으로 초대한다.")
    // 다른 회원을 가족으로 초대
    @PostMapping
    public ResponseEntity<CommonResponseDTO> inviteToFamily(@RequestBody InviteToFamilyRequest inviteToFamilyRequest) {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        invitationCommandService.inviteUserToFamily(currentUserEmail, inviteToFamilyRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "초대 전송 성공", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);

    }

    // 가족 초대 거절
    @Operation(summary = "받은 가족 초대를 거절", description = "다른 사람이 보낸 가족 초대를 거절한다.")
    @PutMapping("/{invitationId}/rejection")
    public ResponseEntity<CommonResponseDTO> rejectInvitation(@PathVariable @Schema(example = "4") Long invitationId) {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        invitationCommandService.rejectInvitation(invitationId, currentUserEmail);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "초대 거절 성공", invitationId);
        return ResponseEntity.ok(successResponse);
    }

    // 가족 초대 수락
    @Operation(summary = "받은 가족 초대를 수락", description = "다른 사람이 보낸 가족 초대를 수락한다.")
    @PutMapping("/{invitationId}/acceptance")
    public ResponseEntity<CommonResponseDTO> acceptInvitation(@PathVariable @Schema(example = "5") Long invitationId) {

        String currentUseremail = UserSecurity.getCurrentUserEmail();

        invitationCommandService.acceptInvitation(invitationId, currentUseremail);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "초대 수락 성공", invitationId);
        return ResponseEntity.ok(successResponse);
    }


}
