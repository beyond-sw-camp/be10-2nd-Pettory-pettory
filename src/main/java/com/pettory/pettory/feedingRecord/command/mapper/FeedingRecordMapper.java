package com.pettory.pettory.feedingRecord.command.mapper;

import com.pettory.pettory.feedingRecord.command.application.dto.FeedingRecordCreateRequest;
import com.pettory.pettory.feedingRecord.command.domain.aggregate.FeedingRecord;
import com.pettory.pettory.pet.command.domain.aggregate.Pet;
import com.pettory.pettory.user.command.domain.aggregate.User;

public class FeedingRecordMapper {
    public static FeedingRecord toEntity(User user, Pet pet, FeedingRecordCreateRequest feedingRecordCreateRequest) {
        return FeedingRecord.create(
                user, pet,
                feedingRecordCreateRequest.getFeedingRecordFeedingType(),
                feedingRecordCreateRequest.getFeedingRecordName(),
                feedingRecordCreateRequest.getFeedingRecordAmount(),
                feedingRecordCreateRequest.getFeedingRecordType(),
                feedingRecordCreateRequest.getFeedingRecordMemo(),
                feedingRecordCreateRequest.getFeedingRecordUserInsertDatetime()
        );
    }
}
