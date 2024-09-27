package com.pettory.pettory.user.command.application.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.user.command.application.dto.UserRegisterRequest;
import com.pettory.pettory.user.command.domain.aggregate.User;
import com.pettory.pettory.user.command.domain.repository.UserRepository;
import com.pettory.pettory.user.command.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserCommandService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public void registerUser(UserRegisterRequest userRegisterRequest) {

        User newUser = UserMapper.toEntity(userRegisterRequest);
        newUser.encryptPassword(passwordEncoder.encode(newUser.getUserPassword())); // newUser의 plain pw를 암호화

        // repository에 저장
        userRepository.save(newUser);
    }

    // 커스텀 로그인 필터 동작 시 인증 매니저에 의해 자동으로 호출되는 메소드
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        // 인증 토큰에 담긴 userEmail이 메소드로 넘어오므로 해당 값을 기준으로 DB에서 조회한다.
        User loginUser = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException(userEmail + "가 존재하지 않습니다."));


        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + loginUser.getUserRole().name()));

        // id(email), pw, 권한
        return new org.springframework.security.core.userdetails.User(loginUser.getUserEmail(), loginUser.getUserPassword(), grantedAuthorities);
    }
}
