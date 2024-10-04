package com.pettory.mainserver.walkingGroupApplication.command.infrastructure.repository;

import com.pettory.mainserver.walkingGroupApplication.command.domain.aggregate.WalkingGroupApplication;
import com.pettory.mainserver.walkingGroupApplication.command.domain.repository.WalkingGroupApplicationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWalkingGroupApplicationRepository extends WalkingGroupApplicationRepository, JpaRepository<WalkingGroupApplication, Integer> {
}
