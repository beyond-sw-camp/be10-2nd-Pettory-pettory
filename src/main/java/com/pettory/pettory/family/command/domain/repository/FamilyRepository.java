package com.pettory.pettory.family.command.domain.repository;

import com.pettory.pettory.family.command.domain.aggregate.Family;

import java.util.Optional;

public interface FamilyRepository {

    Optional<Family> findById(Long sendUserId);

    Family save(Family newFamily);

}
