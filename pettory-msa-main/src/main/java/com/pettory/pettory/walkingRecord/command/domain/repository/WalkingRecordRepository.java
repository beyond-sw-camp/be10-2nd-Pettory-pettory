package com.pettory.pettory.walkingRecord.command.domain.repository;

import com.pettory.pettory.walkingRecord.command.domain.aggregate.WalkingRecord;

import java.util.Optional;

public interface WalkingRecordRepository {
    WalkingRecord save(WalkingRecord newWalkingRecord);

    Optional<WalkingRecord> findById(Long walkingRecordId);
}
