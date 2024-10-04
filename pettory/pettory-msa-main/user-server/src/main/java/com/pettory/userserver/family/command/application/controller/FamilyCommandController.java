package com.pettory.userserver.family.command.application.controller;

import com.pettory.userserver.common.CommonResponseDTO;
import com.pettory.userserver.family.command.application.service.FamilyCommandService;
import com.pettory.userserver.security.util.UserSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
