package com.pettory.pettory.user.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindPasswordRequest {
    @NotBlank(message = "유효한 이메일을 입력하세요")
    private String userEmail;
}
