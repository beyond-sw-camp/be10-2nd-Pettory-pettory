package com.pettory.pettory.user.query.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.security.util.UserSecurity;
import com.pettory.pettory.user.query.dto.UserInfoResponse;
import com.pettory.pettory.user.query.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserQueryController {

    private final UserQueryService userQueryService;

    // 회원이 자신의 회원 정보 조회
    @GetMapping("/email")
    public ResponseEntity<CommonResponseDTO> showUserInfo() {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        // 회원 정보 조회
        UserInfoResponse user = userQueryService.getUserInfo(currentUserEmail);
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "회원 정보 조회 성공", user);
        return ResponseEntity.ok(successResponse);
    }
}
