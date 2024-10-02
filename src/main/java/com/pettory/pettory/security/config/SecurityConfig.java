package com.pettory.pettory.security.config;

import com.pettory.pettory.security.handler.JwtAuthenticationEntryPoint;
import com.pettory.pettory.security.filter.CustomAuthenticationFilter;
import com.pettory.pettory.security.filter.JwtFilter;
import com.pettory.pettory.security.handler.JwtAccessDeniedHandler;
import com.pettory.pettory.security.handler.LoginFailureHandler;
import com.pettory.pettory.security.handler.LoginSuccessHandler;
import com.pettory.pettory.security.util.JwtUtil;
import com.pettory.pettory.user.command.application.service.UserCommandService;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserCommandService userCommandService;
    private final Environment env;
    private final JwtUtil jwtUtil;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> {
                    authz.requestMatchers(new AntPathRequestMatcher("/login", "POST")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/users/**", "POST")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/users/**", "GET")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/users/**", "DELETE")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/families/**", "GET")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/families/**", "POST")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/families/**", "PUT")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/invitations/**", "GET")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/invitations/**", "PUT")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/pets/**", "GET")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/pets/**", "POST")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/pets/**", "PUT")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/pets/**", "DELETE")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/walking-records/**", "GET")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/walking-records/**", "POST")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/walking-records/**", "PUT")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/walking-records/**", "DELETE")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/feeding-records/**", "GET")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/feeding-records/**", "POST")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/feeding-records/**", "PUT")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/feeding-records/**", "DELETE")).hasAuthority("ROLE_USER")
                            .requestMatchers(new AntPathRequestMatcher("/ws/**","POST")).permitAll()
                            .requestMatchers(new AntPathRequestMatcher("/ws/**","GET")).permitAll()
                            .anyRequest().authenticated();
                })
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // JWT 토큰 확인 필터 추가
        http.addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        // 커스텀 로그인 필터 추가
        // default 인증 담당 필터보다 앞에 커스텀 필터 추가
        http.addFilterBefore(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // 인증, 인가 실패 핸들러 설정
        http.exceptionHandling(
                exceptionHandling -> {
                    exceptionHandling.accessDeniedHandler(new JwtAccessDeniedHandler());
                    exceptionHandling.authenticationEntryPoint(new JwtAuthenticationEntryPoint());
                }
        );

        return http.build();
    }

    private Filter getAuthenticationFilter() {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter();   // 커스텀 로그인 필터
        customAuthenticationFilter.setAuthenticationManager(getAuthenticationManager());    // 인증 매니저
        customAuthenticationFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler(env));  // 인증 성공 후 작업
        customAuthenticationFilter.setAuthenticationFailureHandler(new LoginFailureHandler());
        return customAuthenticationFilter;
    }

    // 커스텀 로그인 필터에 설정할 인증 매니저
    private AuthenticationManager getAuthenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userCommandService);
        return new ProviderManager(provider);
    }
}
