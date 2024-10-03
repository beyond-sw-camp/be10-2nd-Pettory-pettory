package com.pettory.pettory.walkingGroupApplication.command.mapper;

import com.pettory.pettory.walkingGroupApplication.command.domain.aggregate.RegisterWalkingGroup;
import com.pettory.pettory.walkingGroupApplication.command.domain.aggregate.WalkingGroupApplication;

public class RegisterWalkingGroupMapper {

    public static RegisterWalkingGroup toEntity(WalkingGroupApplication walkingGroupApplication) {
        return RegisterWalkingGroup.create(
            walkingGroupApplication.getWalkingGroupId(),
            walkingGroupApplication.getUserId()
        );
    }
}
