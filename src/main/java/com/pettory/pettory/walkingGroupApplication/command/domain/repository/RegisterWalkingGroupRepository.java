package com.pettory.pettory.walkingGroupApplication.command.domain.repository;

import com.pettory.pettory.walkingGroupApplication.command.domain.aggregate.RegisterWalkingGroup;

import java.util.Optional;

public interface RegisterWalkingGroupRepository {
    Optional<RegisterWalkingGroup> findById(String registerWalkingGroupId);

    void deleteById(int registerWalkingGroupId);
}
