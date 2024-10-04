package com.pettory.userserver.pet.command.domain.repository;

import com.pettory.userserver.pet.command.domain.aggregate.PetAccess;

public interface PetAccessRepository {
    PetAccess save(PetAccess newPetAccess);
}
