package com.pettory.pettory.jointshopping.command.infrastructure.repository;

import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingGroup;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingParticipationUser;
import com.pettory.pettory.jointshopping.command.domain.repository.JointShoppingGroupRepository;
import com.pettory.pettory.jointshopping.command.domain.repository.JointShoppingParticipationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJointShoppingParticipationRepository extends JointShoppingParticipationRepository, JpaRepository<JointShoppingParticipationUser, Long> {
}
