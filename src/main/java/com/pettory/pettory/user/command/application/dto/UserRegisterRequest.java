package com.pettory.pettory.user.command.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class UserRegisterRequest {

    // 회원가입 필수 필드: 이메일, 닉네임, 이름, 비밀번호, 생년월일
    @NotBlank
    @Email(message = "유효한 이메일 주소를 입력하세요.")  // 이메일 형식인지 검증하는 어노테이션
    private String userEmail;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\\$%\\^&\\*])[A-Za-z\\d!@#\\$%\\^&\\*]{8,}$",
            message = "비밀번호는 최소 8자 이상, 대소문자, 숫자, 특수문자를 모두 포함해주세요.")
    private String userPassword;
    @NotBlank
    private String userNickname;
    @NotBlank
    private String userName;
    @NotNull    // 문자열이 아니므로 @NotBlank 가 아니라 @NotNull 사용
    private LocalDate userBirth;

}
