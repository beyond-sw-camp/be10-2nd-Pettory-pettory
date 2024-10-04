package com.pettory.userserver.walkingRecord.command.mapper;

import com.pettory.userserver.pet.command.domain.aggregate.Pet;
import com.pettory.userserver.user.command.domain.aggregate.User;
import com.pettory.userserver.walkingRecord.command.application.dto.WalkingRecordCreateRequest;
import com.pettory.userserver.walkingRecord.command.domain.aggregate.WalkingRecord;

public class WalkingRecordMapper {
    public static WalkingRecord toEntity(User user, Pet pet, WalkingRecordCreateRequest walkingRecordCreateRequest) {
        return WalkingRecord.create(
                user, pet,
                walkingRecordCreateRequest.getWalkingRecordDate(),
                walkingRecordCreateRequest.getWalkingRecordDuration(),
                walkingRecordCreateRequest.getWalkingRecordPoopCount(),
                walkingRecordCreateRequest.getWalkingRecordWaterAmount(),
                walkingRecordCreateRequest.getWalkingRecordMemo()
        );
    }
}
