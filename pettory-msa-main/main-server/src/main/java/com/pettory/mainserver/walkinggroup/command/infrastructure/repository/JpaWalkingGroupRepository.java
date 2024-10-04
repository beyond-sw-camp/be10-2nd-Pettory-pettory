package com.pettory.mainserver.walkinggroup.command.infrastructure.repository;

import com.pettory.mainserver.walkinggroup.command.domain.aggregate.WalkingGroup;
import com.pettory.mainserver.walkinggroup.command.domain.repository.WalkingGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWalkingGroupRepository extends WalkingGroupRepository, JpaRepository<WalkingGroup, Integer> {
}
