package com.pettory.userserver.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pettory.userserver.security.dto.ExceptionResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;


/* 인증 실패 시 응답 설정 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        /* 유효한 자격 증명(token)을 제공하지 않고 접근하려는 상황 => 401 오류 */
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(new ExceptionResponse(401, "인증 실패")));
    }
}