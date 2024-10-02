package com.pettory.pettory.walkingGroupApplication.command.infrastructure.repository;

import com.pettory.pettory.walkingGroupApplication.command.domain.aggregate.RegisterWalkingGroup;
import com.pettory.pettory.walkingGroupApplication.command.domain.repository.RegisterWalkingGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

;

public interface JpaRegisterWalkingGroupRepository extends RegisterWalkingGroupRepository, JpaRepository<RegisterWalkingGroup, Integer> {
}
