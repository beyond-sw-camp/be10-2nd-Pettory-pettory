package com.pettory.pettory.family.command.infrastructure.repository;

import com.pettory.pettory.family.command.domain.aggregate.Family;
import com.pettory.pettory.family.command.domain.repository.FamilyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAFamilyRepository extends JpaRepository<Family, Long>, FamilyRepository {

}
