package com.pettory.pettory.user.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.exception.AlreadyRegisterException;
import com.pettory.pettory.security.util.UserSecurity;
import com.pettory.pettory.security.util.VerifyUtil;
import com.pettory.pettory.user.command.application.dto.*;
import com.pettory.pettory.user.command.application.service.UserCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Pettory 회원 컨트롤러", description = "회원 가입/탈퇴/비밀번호변경/회원정보조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserCommandController {

    private final UserCommandService userCommandService;


    // 회원가입
    @Operation(summary = "회원 가입",
            description = "새로운 회원을 가입하게 한다.")
    @PostMapping
    public ResponseEntity<CommonResponseDTO> registerUser(@Valid @RequestBody @Schema UserRegisterRequest userRegisterRequest) {


        userCommandService.registerUser(userRegisterRequest);

        // 회원가입 성공 시 응답
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "회원가입 성공", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);

    }

//    // 회원 탈퇴
//    @DeleteMapping
//    public ResponseEntity<CommonResponseDTO> deleteUser(@RequestBody @Schema UserDeleteRequest userDeleteRequest) {
//
//        String currentUserEmail = UserSecurity.getCurrentUserEmail();
//
//        userCommandService.deleteUser(currentUserEmail, userDeleteRequest);
//
//        // 회원 탈퇴 성공 시 응답
//        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "회원 탈퇴 성공", null);
//        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
//    }

    // 비밀번호 찾기
//    @PostMapping("/passwords")
//    public ResponseEntity<CommonResponseDTO> findPasswords(@RequestBody FindPasswordRequest findPasswordRequest) {
//
//        userCommandService.getNewPasswords(findPasswordRequest);
//
//        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "새로운 비밀번호 발송 성공", null);
//        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
//    }

    @Operation(summary = "회원 비밀번호 변경", description = "회원의 비밀번호를 변경한다.")
    // 비밀번호 변경
    @PutMapping("/passwords")
    public ResponseEntity<CommonResponseDTO> updatePasswords(@Valid @RequestBody @Schema ChangePasswordRequest changePasswordRequest) {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        userCommandService.changePasswords(currentUserEmail, changePasswordRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "비밀번호 변경 성공", null);
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

}
