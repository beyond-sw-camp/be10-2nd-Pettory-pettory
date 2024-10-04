package com.pettory.userserver.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pettory.userserver.security.dto.ExceptionResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;


/* 인가 실패 시 응답 설정 */
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        /* 리소스에 접근 권한이 없는데 접근하여 인가 되지 않은 경우 => 403 오류 */
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(new ExceptionResponse(403, "인가 실패")));
    }
}
