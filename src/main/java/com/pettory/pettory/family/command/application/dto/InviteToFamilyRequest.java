package com.pettory.pettory.family.command.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class InviteToFamilyRequest {

    @NotBlank(message = "초대할 회원의 이메일을 입력하세요.")
    @Email(message = "유효한 이메일 주소를 입력하세요.")
    private String receivedUserEmail;

}
