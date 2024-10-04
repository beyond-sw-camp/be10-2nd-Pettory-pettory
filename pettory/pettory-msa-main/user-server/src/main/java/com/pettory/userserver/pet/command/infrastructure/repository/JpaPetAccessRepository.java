package com.pettory.userserver.pet.command.infrastructure.repository;

import com.pettory.userserver.pet.command.domain.aggregate.PetAccess;
import com.pettory.userserver.pet.command.domain.repository.PetAccessRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPetAccessRepository extends PetAccessRepository, JpaRepository<PetAccess, Long> {
}
