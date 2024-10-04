package com.pettory.userserver.user.query.mapper;

import com.pettory.userserver.user.query.dto.UserEmailResponse;
import com.pettory.userserver.user.query.dto.UserInfoResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    // 회원의 이메일을 받아 회원 id를 반환하는 메소드
    UserEmailResponse findUserIdByEmail(@Param("userEmail") String userEmail);
    // 회원의 이메일을 받아 회원 id를 반환하는 메소드
    UserInfoResponse findUserInfoByEmail(@Param("userEmail") String userEmail);

    Optional<Long> findFamilyIdByUserId(Long userId);
}
