package com.pettory.userserver.user.query.service;

import com.pettory.userserver.exception.NotFoundException;
import com.pettory.userserver.security.util.UserSecurity;
import com.pettory.userserver.user.query.dto.UserInfoResponse;
import com.pettory.userserver.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserMapper userMapper;

    // 회원 정보 조회
    @Transactional(readOnly = true)
    public UserInfoResponse getUserInfo(String userEmail) {

        UserSecurity.validateCurrentUser(userEmail);

        // 회원 정보 조회
        UserInfoResponse userInfo = userMapper.findUserInfoByEmail(userEmail);

        if (userInfo == null) {
            throw new NotFoundException("회원 정보를 찾을 수 없습니다.");
        }
        return userInfo;
    }
}
