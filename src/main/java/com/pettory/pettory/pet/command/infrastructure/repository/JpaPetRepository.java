package com.pettory.pettory.pet.command.infrastructure.repository;

import com.pettory.pettory.pet.command.domain.aggregate.Pet;
import com.pettory.pettory.pet.command.domain.repository.PetRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPetRepository extends PetRepository, JpaRepository<Pet, Long> {
}
