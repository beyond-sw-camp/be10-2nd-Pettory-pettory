package com.pettory.pettory.user.command.mapper;

import com.pettory.pettory.user.command.application.dto.UserRegisterRequest;
import com.pettory.pettory.user.command.domain.aggregate.User;

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
