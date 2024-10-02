package com.pettory.pettory.walkingGroupApplication.command.domain.repository;

import com.pettory.pettory.walkingGroupApplication.command.domain.aggregate.RegisterWalkingGroup;

import java.util.Optional;

public interface RegisterWalkingGroupRepository {

    RegisterWalkingGroup save(RegisterWalkingGroup newRegisterWalkingGroup);

    Optional<RegisterWalkingGroup> findById(int registerWalkingGroupId);

    void deleteById(int registerWalkingGroupId);

}
