package com.pettory.pettory.user.query.mapper;

import com.pettory.pettory.user.query.dto.UserEmailResponse;
import com.pettory.pettory.user.query.dto.UserInfoResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    // 회원의 이메일을 받아 회원 id를 반환하는 메소드
    UserEmailResponse findUserIdByEmail(@Param("userEmail") String userEmail);
    // 회원의 이메일을 받아 회원 id를 반환하는 메소드
    UserInfoResponse findUserInfoByEmail(@Param("userEmail") String userEmail);
}
