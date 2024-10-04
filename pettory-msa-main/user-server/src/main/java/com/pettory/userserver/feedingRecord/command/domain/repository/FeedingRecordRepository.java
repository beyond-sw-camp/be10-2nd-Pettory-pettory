package com.pettory.userserver.feedingRecord.command.domain.repository;

import com.pettory.userserver.feedingRecord.command.domain.aggregate.FeedingRecord;

import java.util.Optional;

public interface FeedingRecordRepository {
    FeedingRecord save(FeedingRecord newFeedingRecord);

    Optional<FeedingRecord> findById(Long feedingRecordId);
}
