package com.pettory.pettory.user.command.application.dto;

import com.pettory.pettory.user.command.domain.aggregate.UserRole;
import com.pettory.pettory.user.command.domain.aggregate.UserState;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "회원 가입 요청 파라미터")
@Getter
@RequiredArgsConstructor
public class UserRegisterRequest {
    @Schema(description = "사용자 이메일", example = "ptr@gmail.com")
    // 회원가입 필수 필드: 이메일, 닉네임, 이름, 비밀번호, 생년월일
    @NotBlank
    @Email(message = "유효한 이메일 주소를 입력하세요.")  // 이메일 형식인지 검증하는 어노테이션
    private String userEmail;

    @Schema(description = "사용자 비밀번호", example = "*Aa!!yuriLK2")
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\\$%\\^&\\*])[A-Za-z\\d!@#\\$%\\^&\\*]{8,}$",
            message = "비밀번호는 최소 8자 이상, 대소문자, 숫자, 특수문자를 모두 포함해주세요.")
    private String userPassword;

    @Schema(description = "사용자 닉네임", example = "펫토리")
    @NotBlank
    private String userNickname;

    @Schema(description = "사용자 이름", example = "김펫토리")
    @NotBlank
    private String userName;

    @Schema(description = "사용자 생년월일", example = "2001-02-13")
    @NotNull    // 문자열이 아니므로 @NotBlank 가 아니라 @NotNull 사용
    private LocalDate userBirth;

    public UserRegisterRequest(String userEmail, String userPassword, String userNickname, String userName, LocalDate userBirth) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.userName = userName;
        this.userBirth = userBirth;
    }
}
