package com.pettory.userserver.user.command.mapper;

import com.pettory.userserver.user.command.application.dto.UserRegisterRequest;
import com.pettory.userserver.user.command.domain.aggregate.User;

public class UserMapper {
    public static User toEntity(UserRegisterRequest userRegisterRequest) {
        return User.create(
                userRegisterRequest.getUserEmail(),
                userRegisterRequest.getUserPassword(),
                userRegisterRequest.getUserNickname(),
                userRegisterRequest.getUserName(),
                userRegisterRequest.getUserBirth()
        );
    }
}
