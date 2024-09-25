package com.pettory.pettory.walkingGroupApplication.command.infrastructure.repository;

import com.pettory.pettory.walkingGroupApplication.command.domain.aggregate.WalkingGroupApplication;
import com.pettory.pettory.walkingGroupApplication.command.domain.repository.WalkingGroupApplicationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWalkingGroupApplicationRepository extends WalkingGroupApplicationRepository, JpaRepository<WalkingGroupApplication, Integer> {
}
