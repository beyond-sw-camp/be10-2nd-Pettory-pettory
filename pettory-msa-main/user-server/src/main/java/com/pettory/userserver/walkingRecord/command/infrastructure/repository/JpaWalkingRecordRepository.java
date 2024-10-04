package com.pettory.userserver.walkingRecord.command.infrastructure.repository;

import com.pettory.userserver.walkingRecord.command.domain.aggregate.WalkingRecord;
import com.pettory.userserver.walkingRecord.command.domain.repository.WalkingRecordRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWalkingRecordRepository extends WalkingRecordRepository, JpaRepository<WalkingRecord, Long> {
}
