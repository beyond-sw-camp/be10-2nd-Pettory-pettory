package com.pettory.pettory.walkingGroupRecord.command.mapper;

import com.pettory.pettory.walkingGroupRecord.command.application.dto.WalkingGroupRecordCreateRequest;
import com.pettory.pettory.walkingGroupRecord.command.domain.aggregate.WalkingGroupRecord;

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
