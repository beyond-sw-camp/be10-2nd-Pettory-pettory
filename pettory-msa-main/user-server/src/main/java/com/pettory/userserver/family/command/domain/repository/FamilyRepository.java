package com.pettory.userserver.family.command.domain.repository;

import com.pettory.userserver.family.command.domain.aggregate.Family;

import java.util.Optional;

public interface FamilyRepository {

    Optional<Family> findById(Long sendUserId);

    Family save(Family newFamily);

}
