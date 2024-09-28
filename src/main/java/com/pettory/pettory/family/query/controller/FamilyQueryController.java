package com.pettory.pettory.family.query.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.family.query.dto.JoinedFamilyRequest;
import com.pettory.pettory.family.query.dto.JoinedFamilyResponse;
import com.pettory.pettory.family.query.service.FamilyQueryService;
import com.pettory.pettory.security.util.UserSecurity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("family/")
@Slf4j
public class FamilyQueryController {

    private final FamilyQueryService familyQueryService;

    // 회원이 속한 가족 조회
    // 가족 이름, 가족구성원 수, 가족 구성원 이름
    @GetMapping("/members")
    public ResponseEntity<CommonResponseDTO> getJoinedFamilyInfo() {
        // 현재 로그인한 사용자의 이메일 가져오기
        String currentUserEmail = UserSecurity.getCurrentUserEmail();
        log.info("currentUserEmail: " + currentUserEmail);
        // 가족 정보 조회
        JoinedFamilyResponse family = familyQueryService.getFamilyInfo(currentUserEmail);
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "가족 조회 성공", family);
        return ResponseEntity.ok(successResponse);
    }
    
}

