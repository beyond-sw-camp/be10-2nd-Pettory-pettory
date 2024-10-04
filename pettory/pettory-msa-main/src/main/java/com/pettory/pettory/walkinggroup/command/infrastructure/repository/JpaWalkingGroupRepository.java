package com.pettory.pettory.walkinggroup.command.infrastructure.repository;

import com.pettory.pettory.walkinggroup.command.domain.aggregate.WalkingGroup;
import com.pettory.pettory.walkinggroup.command.domain.repository.WalkingGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWalkingGroupRepository extends WalkingGroupRepository, JpaRepository<WalkingGroup, Integer> {
}
