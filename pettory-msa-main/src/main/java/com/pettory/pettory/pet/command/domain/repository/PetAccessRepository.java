package com.pettory.pettory.pet.command.domain.repository;

import com.pettory.pettory.pet.command.domain.aggregate.PetAccess;

public interface PetAccessRepository {
    PetAccess save(PetAccess newPetAccess);
}
