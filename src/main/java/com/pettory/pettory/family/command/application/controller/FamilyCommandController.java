package com.pettory.pettory.family.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.family.command.application.dto.KickMemberRequest;
import com.pettory.pettory.family.command.application.service.FamilyCommandService;
import com.pettory.pettory.family.command.application.service.InvitationCommandService;
import com.pettory.pettory.security.util.UserSecurity;
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
@RequestMapping("/families")
public class FamilyCommandController {

    private final FamilyCommandService familyCommandService;

    @Operation(summary = "가족에서 회원 삭제", description = "가족에서 구성원(회원)을 삭제한다.")
    // 가족구성원을 가족에서 삭제
    @PutMapping("/members/{memberId}")
    public ResponseEntity<CommonResponseDTO> kickFromFamily(@PathVariable @Schema(example = "") Long memberId) {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        familyCommandService.kickFromFamily(currentUserEmail, memberId);

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.OK.value(), "가족구성원 삭제 성공", null);
        return ResponseEntity.ok(commonResponseDTO);
    }
}
