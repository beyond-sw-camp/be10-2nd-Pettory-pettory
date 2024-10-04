package com.pettory.mainserver.walkingGroupApplication.command.infrastructure.repository;

import com.pettory.mainserver.walkingGroupApplication.command.domain.aggregate.RegisterWalkingGroup;
import com.pettory.mainserver.walkingGroupApplication.command.domain.repository.RegisterWalkingGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

;

public interface JpaRegisterWalkingGroupRepository extends RegisterWalkingGroupRepository, JpaRepository<RegisterWalkingGroup, Integer> {
}
