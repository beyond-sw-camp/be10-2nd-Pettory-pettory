package com.pettory.userserver.family.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class InviteToFamilyRequest {

    @Schema(example = "mc@naver.com")
    @NotBlank(message = "초대할 회원의 이메일을 입력하세요.")
    @Email(message = "유효한 이메일 주소를 입력하세요.")
    private String receivedUserEmail;

}
