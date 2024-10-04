package com.pettory.mainserver.jointshopping.command.infrastructure.repository;

import com.pettory.mainserver.jointshopping.command.domain.aggregate.JointShoppingGroup;
import com.pettory.mainserver.jointshopping.command.domain.aggregate.JointShoppingParticipationUser;
import com.pettory.mainserver.jointshopping.command.domain.repository.JointShoppingGroupRepository;
import com.pettory.mainserver.jointshopping.command.domain.repository.JointShoppingParticipationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJointShoppingParticipationRepository extends JointShoppingParticipationRepository, JpaRepository<JointShoppingParticipationUser, Long> {
}
