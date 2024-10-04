package com.pettory.mainserver.walkingGroupApplication.command.domain.repository;

import com.pettory.mainserver.walkingGroupApplication.command.domain.aggregate.WalkingGroupApplication;

import java.util.Optional;

public interface WalkingGroupApplicationRepository {

    WalkingGroupApplication save(WalkingGroupApplication newWalkingGroupApplication);

    Optional<WalkingGroupApplication> findById(int walkingGroupApplicationId);

    void deleteById(int walkingGroupApplicationId);
}
