package com.pettory.pettory.security.dto;

import lombok.Getter;
import lombok.Setter;

// id, pw를 전달받는 타입
@Getter
@Setter
public class LoginRequest {
    private String userEmail;
    private String userPwd;
}
