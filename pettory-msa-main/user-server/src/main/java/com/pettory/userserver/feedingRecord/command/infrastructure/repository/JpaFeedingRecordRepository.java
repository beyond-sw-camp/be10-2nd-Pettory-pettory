package com.pettory.userserver.feedingRecord.command.infrastructure.repository;

import com.pettory.userserver.feedingRecord.command.domain.aggregate.FeedingRecord;
import com.pettory.userserver.feedingRecord.command.domain.repository.FeedingRecordRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFeedingRecordRepository extends FeedingRecordRepository, JpaRepository<FeedingRecord, Long> {
}

