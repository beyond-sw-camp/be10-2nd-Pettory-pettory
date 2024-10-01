package com.pettory.pettory.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pettory.pettory.security.dto.LoginRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.ArrayList;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    // 해당 요청이 올 때 이 필터가 동작하도록 설정한다.
    // 가로챌 요청을 전달해야 한다.
    public CustomAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

    // 이 필터가 동작 시 수행할 내용
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // request body에 담긴 정보(id, pw)를 LoginRequest 타입에 담아준다. (Authentication 타입으로 만들어 반환한다.)
        // Controller의 @RequestBody 어노테이션을 통해 자동으로 convert 되었던 부분을 filter에서 직접 처리하는 과정.
        LoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);    // json -> java 객체 변환을 직접 처리

        // UsernamePasswordAuthenticationToken 을 만드는 과정
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(creds.getUserEmail(), creds.getUserPwd(), new ArrayList<>())
        );

    }
}

// principal: 인증 객체
// credential: 비밀번호
// authorities: 권한들
