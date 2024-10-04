package com.pettory.mainserver.walkinggroup.command.domain.repository;

import com.pettory.mainserver.walkinggroup.command.domain.aggregate.WalkingGroup;

import java.util.Optional;

public interface WalkingGroupRepository {
    WalkingGroup save(WalkingGroup newWalkingGroup);

    Optional<WalkingGroup> findById(int walkingGroupId);

    void deleteById(int walkingGroupId);
}
