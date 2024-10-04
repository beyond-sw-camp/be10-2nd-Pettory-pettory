package com.pettory.mainserver.walkingGroupRecord.command.mapper;

import com.pettory.mainserver.walkingGroupRecord.command.application.dto.WalkingGroupRecordCreateRequest;
import com.pettory.mainserver.walkingGroupRecord.command.domain.aggregate.WalkingGroupRecord;

public class WalkingGroupRecordMapper {
    public static WalkingGroupRecord toEntity(WalkingGroupRecordCreateRequest walkingGroupRecordRequest) {
        return WalkingGroupRecord.create(
                walkingGroupRecordRequest.getWalkingGroupDate(),
                walkingGroupRecordRequest.getWalkingGroupRecordDuration(),
                walkingGroupRecordRequest.getWalkingGroupRouteStart(),
                walkingGroupRecordRequest.getWalkingGroupRouteEnd(),
                walkingGroupRecordRequest.getWalkingGroupId()
        );
    }
}
