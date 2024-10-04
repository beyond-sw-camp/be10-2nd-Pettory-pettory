package com.pettory.userserver.feedingRecord.command.mapper;

import com.pettory.userserver.feedingRecord.command.application.dto.FeedingRecordCreateRequest;
import com.pettory.userserver.feedingRecord.command.domain.aggregate.FeedingRecord;
import com.pettory.userserver.pet.command.domain.aggregate.Pet;
import com.pettory.userserver.user.command.domain.aggregate.User;

public class FeedingRecordMapper {
    public static FeedingRecord toEntity(User user, Pet pet, FeedingRecordCreateRequest feedingRecordCreateRequest) {
        return FeedingRecord.create(
                user, pet,
                feedingRecordCreateRequest.getFeedingRecordFeedingType(),
                feedingRecordCreateRequest.getFeedingRecordName(),
                feedingRecordCreateRequest.getFeedingRecordType(),
                feedingRecordCreateRequest.getFeedingRecordAmount(),
                feedingRecordCreateRequest.getFeedingRecordMemo(),
                feedingRecordCreateRequest.getFeedingRecordUserInsertDatetime()
        );
    }
}
