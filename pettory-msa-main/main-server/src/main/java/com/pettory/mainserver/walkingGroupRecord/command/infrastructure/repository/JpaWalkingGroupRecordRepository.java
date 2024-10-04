package com.pettory.mainserver.walkingGroupRecord.command.infrastructure.repository;

import com.pettory.mainserver.walkingGroupRecord.command.domain.aggregate.WalkingGroupRecord;
import com.pettory.mainserver.walkingGroupRecord.command.domain.repository.WalkingGroupRecordRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWalkingGroupRecordRepository extends WalkingGroupRecordRepository, JpaRepository<WalkingGroupRecord, Integer> {
}
