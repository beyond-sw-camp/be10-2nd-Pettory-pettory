package com.pettory.userserver.family.query.controller;

import com.pettory.userserver.common.CommonResponseDTO;
import com.pettory.userserver.family.query.dto.JoinedFamilyResponse;
import com.pettory.userserver.family.query.service.FamilyQueryService;
import com.pettory.userserver.security.util.UserSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Pettory 가족 컨트롤러", description = "가족 정보 조회/받은 가족 초대 조회/다른 회원을 가족으로 초대/초대 수락/초대 거절/가족에서 회원 삭제")
@RestController
@RequiredArgsConstructor
@RequestMapping("/families")
@Slf4j
public class FamilyQueryController {

    private final FamilyQueryService familyQueryService;

    // 회원이 속한 가족 조회
    // 가족 이름, 가족구성원 수, 가족 구성원 이름
    @Operation(summary = "가족 정보 조회", description = "회원이 속한 가족의 정보를 조회한다.")
    @GetMapping("/members")
    public ResponseEntity<CommonResponseDTO> getJoinedFamilyInfo() {
        // 현재 로그인한 사용자의 이메일 가져오기
        String currentUserEmail = UserSecurity.getCurrentUserEmail();
        log.info("currentUserEmail: " + currentUserEmail);
        // 가족 정보 조회
        JoinedFamilyResponse family = familyQueryService.getFamilyInfo(currentUserEmail);
        log.info("family: " + family);
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "가족 조회 성공", family);
        return ResponseEntity.ok(successResponse);
    }
    
}

