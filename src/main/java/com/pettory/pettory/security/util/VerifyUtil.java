package com.pettory.pettory.security.util;


import java.util.UUID;

public class VerifyUtil {

    // 회원의 임시 비밀번호를 생성하는 메소드
    public static String generateTempPassword() {
        return UUID.randomUUID().toString().substring(0, 8);    // 8자리 임시 비밀번호
    }

    // 이메일 인증을 위한 6자리 인증 코드를 생성하는 메소스
    public static String generateVerifyCode() {
        int randomCode = (int)(Math.random() * 900000) + 100000;
        return Integer.toString(randomCode);
    }
}
