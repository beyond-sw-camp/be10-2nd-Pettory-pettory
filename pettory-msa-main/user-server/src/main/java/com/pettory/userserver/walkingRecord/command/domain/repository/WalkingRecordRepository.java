package com.pettory.userserver.walkingRecord.command.domain.repository;

import com.pettory.userserver.walkingRecord.command.domain.aggregate.WalkingRecord;

import java.util.Optional;

public interface WalkingRecordRepository {
    WalkingRecord save(WalkingRecord newWalkingRecord);

    Optional<WalkingRecord> findById(Long walkingRecordId);
}
