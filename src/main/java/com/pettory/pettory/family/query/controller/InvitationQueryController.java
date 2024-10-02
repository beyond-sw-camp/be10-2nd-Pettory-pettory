package com.pettory.pettory.family.query.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.family.query.dto.ReceivedInvitationResponse;
import com.pettory.pettory.family.query.service.InvitationQueryService;
import com.pettory.pettory.security.util.UserSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pettory 가족 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("invitations")
public class InvitationQueryController {

    private final InvitationQueryService invitationQueryService;

    // 받은 초대 조회
    @Operation(summary = "받은 가족 초대 조회", description = "회원이 받은 가족 초대를 조회한다.")
    @GetMapping
    public ResponseEntity<CommonResponseDTO> showReceivedInvitation() {
        // 현재 로그인 한 사용자의 이메일 가져오기
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        List<ReceivedInvitationResponse> invitations = invitationQueryService.getReceivedInvitation(currentUserEmail);

        CommonResponseDTO response = new CommonResponseDTO(HttpStatus.OK.value(), "초대 조회 성공", invitations);
        return ResponseEntity.ok(response);
    }
}
