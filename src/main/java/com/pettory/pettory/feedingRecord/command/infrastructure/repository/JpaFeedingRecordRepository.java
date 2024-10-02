package com.pettory.pettory.feedingRecord.command.infrastructure.repository;

import com.pettory.pettory.feedingRecord.command.domain.aggregate.FeedingRecord;
import com.pettory.pettory.feedingRecord.command.domain.repository.FeedingRecordRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFeedingRecordRepository extends FeedingRecordRepository, JpaRepository<FeedingRecord, Long> {
}

