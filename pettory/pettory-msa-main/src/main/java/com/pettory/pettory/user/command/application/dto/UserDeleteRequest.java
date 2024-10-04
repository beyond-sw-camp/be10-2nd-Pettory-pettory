package com.pettory.pettory.user.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDeleteRequest {
    @Schema(example = "*Aa!!yuriLK2")
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\\$%\\^&\\*])[A-Za-z\\d!@#\\$%\\^&\\*]{8,}$",
            message = "비밀번호는 최소 8자 이상, 대소문자, 숫자, 특수문자를 모두 포함해주세요.")
    private String userPassword;

}
