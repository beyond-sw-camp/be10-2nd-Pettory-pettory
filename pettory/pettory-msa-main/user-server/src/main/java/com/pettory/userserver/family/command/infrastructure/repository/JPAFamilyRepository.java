package com.pettory.userserver.family.command.infrastructure.repository;

import com.pettory.userserver.family.command.domain.aggregate.Family;
import com.pettory.userserver.family.command.domain.repository.FamilyRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAFamilyRepository extends JpaRepository<Family, Long>, FamilyRepository {

}
