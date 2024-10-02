package com.pettory.pettory.walkingGroupApplication.command.mapper;

import com.pettory.pettory.walkingGroupApplication.command.application.dto.WalkingGroupApplicationCreateRequest;
import com.pettory.pettory.walkingGroupApplication.command.domain.aggregate.WalkingGroupApplication;

public class WalkingGroupApplicationMapper {
    public static WalkingGroupApplication toEntity(WalkingGroupApplicationCreateRequest walkingGroupApplicationRequest) {
        return WalkingGroupApplication.create(
                walkingGroupApplicationRequest.getWalkingGroupId(),
                walkingGroupApplicationRequest.getUserId()
        );
    }
}
