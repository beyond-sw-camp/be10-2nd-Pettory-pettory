package com.pettory.pettory.walkingGroupRecord.command.domain.repository;

import com.pettory.pettory.walkingGroupRecord.command.domain.aggregate.WalkingGroupRecord;

import java.util.Optional;

public interface WalkingGroupRecordRepository {
    WalkingGroupRecord save(WalkingGroupRecord newWalkingGroupRecord);

    Optional<WalkingGroupRecord> findById(int walkingGroupRecordId);

    void deleteById(int walkingGroupRecordId);
}
