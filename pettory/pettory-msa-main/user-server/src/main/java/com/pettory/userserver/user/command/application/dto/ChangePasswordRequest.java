package com.pettory.userserver.user.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChangePasswordRequest {
    @Schema(example = "*Aa!!yuriLK2")
    @NotBlank(message = "현재 비밀번호를 입력하세요.")
    private String currentUserPassword;

    @Schema(example = "*Aa!!yuriLK22")
    @NotBlank(message = "새로운 비밀번호를 입력하세요.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\\$%\\^&\\*])[A-Za-z\\d!@#\\$%\\^&\\*]{8,}$",
            message = "비밀번호는 최소 8자 이상, 대소문자, 숫자, 특수문자를 모두 포함해주세요.")
    private String newUserPassword;

    public ChangePasswordRequest(String currentPassword, String newPassword) {
        this.currentUserPassword = currentPassword;
        this.newUserPassword = newPassword;
    }
}
