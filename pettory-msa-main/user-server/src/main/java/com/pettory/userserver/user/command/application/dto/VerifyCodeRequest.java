package com.pettory.userserver.user.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class VerifyCodeRequest {
    @NotBlank
    private String userEmail;
    @NotBlank
    private String verifyCode;
}
