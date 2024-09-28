package com.pettory.pettory.user.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.user.command.application.dto.UserRegisterRequest;
import com.pettory.pettory.user.command.application.service.UserCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserCommandController {
    // TODO: RESTful 메소드 이름으로 고치기!!

    private final UserCommandService userCommandService;

    // 회원가입
    @PostMapping
    public ResponseEntity<CommonResponseDTO> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {


        userCommandService.registerUser(userRegisterRequest);

        // 회원가입 성공 시 응답
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "회원가입 성공", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);

    }
}
