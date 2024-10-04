package com.pettory.mainserver.walkinggroup.command.mapper;

import com.pettory.mainserver.walkinggroup.command.application.dto.WalkingGroupCreateRequest;
import com.pettory.mainserver.walkinggroup.command.domain.aggregate.WalkingGroup;

public class WalkingGroupMapper {
    public static WalkingGroup toEntity(WalkingGroupCreateRequest walkingGroupRequest) {
        return WalkingGroup.create(
                walkingGroupRequest.getWalkingGroupName(),
                walkingGroupRequest.getWalkingGroupInfo(),
                walkingGroupRequest.getWalkingGroupMaximumCount(),
                walkingGroupRequest.getWalkingGroupOwner()
        );
    }
}
