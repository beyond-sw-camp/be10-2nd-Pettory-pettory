package com.pettory.pettory.category.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/categories/**").hasRole("ADMIN")  // ADMIN 권한 필요
                        .anyRequest().authenticated()  // 나머지 요청은 인증 필요
                )
                .formLogin(form -> form
                        .loginPage("/login")  // 커스텀 로그인 페이지 설정 (필요 시)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());  // 로그아웃 설정

        return http.build();  // SecurityFilterChain 반환
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // 비밀번호 암호화 방식
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // 사용자 및 권한 정의
        return username -> {
            if ("admin".equals(username)) {
                return org.springframework.security.core.userdetails.User.withUsername("admin")
                        .password(passwordEncoder().encode("admin123"))
                        .authorities("ROLE_ADMIN")  // 관리자 권한 부여
                        .build();
            } else {
                return org.springframework.security.core.userdetails.User.withUsername("user")
                        .password(passwordEncoder().encode("user123"))
                        .authorities("ROLE_USER")  // 일반 사용자 권한 부여
                        .build();
            }
        };
    }
}

