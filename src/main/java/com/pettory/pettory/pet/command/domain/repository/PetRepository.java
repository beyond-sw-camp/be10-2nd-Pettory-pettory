package com.pettory.pettory.pet.command.domain.repository;

import com.pettory.pettory.pet.command.domain.aggregate.Pet;

import java.util.Optional;

public interface PetRepository {
    Pet save(Pet newPet);

    Optional<Pet> findById(Long petId);

}
