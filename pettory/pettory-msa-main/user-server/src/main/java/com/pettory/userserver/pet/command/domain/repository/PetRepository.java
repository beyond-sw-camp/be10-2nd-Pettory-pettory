package com.pettory.userserver.pet.command.domain.repository;

import com.pettory.userserver.pet.command.domain.aggregate.Pet;

import java.util.Optional;

public interface PetRepository {
    Pet save(Pet newPet);

    Optional<Pet> findById(Long petId);

}
