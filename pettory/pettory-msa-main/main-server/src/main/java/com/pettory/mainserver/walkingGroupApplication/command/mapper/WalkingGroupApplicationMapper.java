package com.pettory.mainserver.walkingGroupApplication.command.mapper;

import com.pettory.mainserver.walkingGroupApplication.command.application.dto.WalkingGroupApplicationCreateRequest;
import com.pettory.mainserver.walkingGroupApplication.command.domain.aggregate.WalkingGroupApplication;

public class WalkingGroupApplicationMapper {
    public static WalkingGroupApplication toEntity(WalkingGroupApplicationCreateRequest walkingGroupApplicationRequest) {
        return WalkingGroupApplication.create(
                walkingGroupApplicationRequest.getWalkingGroupId(),
                walkingGroupApplicationRequest.getUserId()
        );
    }
}
