package com.pettory.pettory.family.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.family.command.application.dto.InviteToFamilyRequest;
import com.pettory.pettory.family.command.application.dto.KickMemberRequest;
import com.pettory.pettory.family.command.application.service.InvitationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/family")
public class InvitationCommandController {
    // TODO: RESTful 메소드 이름으로 고치기!!

    private final InvitationCommandService invitationCommandService;
    ;

    // 다른 회원을 가족으로 초대
    @PostMapping("/invite")
    public ResponseEntity<CommonResponseDTO> inviteToFamily(@RequestBody InviteToFamilyRequest inviteToFamilyRequest) {

        // TODO : 로그인 구현 후 현재 로그인 한 유저id 가져와야 함
        Long invitationSenderUserId = 19L;
        invitationCommandService.inviteUserToFamily(invitationSenderUserId, inviteToFamilyRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "초대 전송 성공", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);

    }

    // 가족 초대 거절
    @PutMapping("/rejection")
    // TODO : 로그인 구현 후 수정?
    public ResponseEntity<CommonResponseDTO> rejectInvitation(@RequestParam Long invitationId) {
        invitationCommandService.rejectInvitation(invitationId);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "초대 거절 성공", null);
        return ResponseEntity.ok(successResponse);
    }


    // 가족 초대 수락
    @PutMapping("/acceptance")
    // TODO : 로그인 구현 후 수정?
    public ResponseEntity<CommonResponseDTO> acceptInvitation(@RequestParam Long invitationId) {
        invitationCommandService.acceptInvitation(invitationId);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "초대 수락 성공", null);
        return ResponseEntity.ok(successResponse);
    }

    // 가족구성원을 가족에서 삭제
    @PutMapping("/kick")
    // TODO: 로그인 구현 후 수정?
    public ResponseEntity<CommonResponseDTO> kickFromFamily(@RequestBody KickMemberRequest kickMemberRequest) {
        Long familyOwnerId = 19L;

        invitationCommandService.kickFromFamily(familyOwnerId, kickMemberRequest.getMemberId());

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.OK.value(), "가족구성원 삭제 성공", null);
        return ResponseEntity.ok(commonResponseDTO);
    }
}
