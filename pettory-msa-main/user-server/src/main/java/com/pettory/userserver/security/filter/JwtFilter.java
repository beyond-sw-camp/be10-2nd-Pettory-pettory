package com.pettory.userserver.security.filter;

import com.pettory.userserver.security.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// OncePerRequestFilter를 상속받아 doFilterInternal을 오버라이딩 한다.
// OncePerRequestFilter: 반드시 한 번만 실행되는 필터
@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;


    // 필터 통과 시 동작하는 메소드
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 요청 헤더에 담긴 토큰의 유효성 판별 및 인증 객체 저장
        String authorizationHeader = request.getHeader("Authorization");
        log.info("Authorization header: {}", authorizationHeader);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            log.info("Token: {}", token);
            if (jwtUtil.validateToken(token)) {
                log.info("유효한 토큰임.");
                // 토큰이 유효시간 내이고, pettory가 서명했으며, 양식에 맞는 경우
                Authentication authentication = jwtUtil.getAuthentication(token);
                // 인증 완료 됨, 이후 인증 필터는 건너뛴다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                log.info("유효한 토큰이 아님!!");
            }
        }

        // 위 if문에 걸리지 않아 Authentication 객체가 설정되지 않으면 다음 필터(인증 필터)가 실행 된다.
        filterChain.doFilter(request, response);
        log.info("authentication 객체 설정 안 됨. 인증 필터 실행.");
    }
}

// jwt 토큰이 유효하면 인증되었으므로 인증 필터를 건너 뛰고 인가 필터로!