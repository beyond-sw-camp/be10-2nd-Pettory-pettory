package com.pettory.pettory.security.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.access.AccessDeniedException;

@Component
@Slf4j
public class UserSecurity {

    private static JwtUtil jwtUtil;

    @Autowired
    public UserSecurity(JwtUtil jwtUtil) {
        UserSecurity.jwtUtil = jwtUtil;
    }

    // 현재 로그인한 사용자의 userEmail을 가져오는 메소드
    public static String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            // UserDetails에서 이메일 정보 가져오기
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            log.info("userDetails.getUsername() :" + userDetails.getUsername());

            return userDetails.getUsername(); // userEmail 의미
        }
        throw new IllegalStateException("로그인 정보가 없습니다.");
    }

    // 현재 로그인한 사용자의 userEmail과 입력받은 userEmail을 검증하는 메소드
    public static void validateCurrentUser(String userEmail) throws AccessDeniedException {
        String currentUserEmail = getCurrentUserEmail();
        if (!currentUserEmail.equals(userEmail)) {
            throw new AccessDeniedException("권한이 없습니다.");
        }
    }


}
