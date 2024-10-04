package com.pettory.userserver.pet.command.infrastructure.repository;

import com.pettory.userserver.pet.command.domain.aggregate.Pet;
import com.pettory.userserver.pet.command.domain.repository.PetRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPetRepository extends PetRepository, JpaRepository<Pet, Long> {
}
