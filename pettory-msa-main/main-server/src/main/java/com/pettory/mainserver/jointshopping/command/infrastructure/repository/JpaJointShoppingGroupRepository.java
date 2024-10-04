package com.pettory.mainserver.jointshopping.command.infrastructure.repository;

import com.pettory.mainserver.jointshopping.command.domain.aggregate.JointShoppingGroup;
import com.pettory.mainserver.jointshopping.command.domain.repository.JointShoppingGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJointShoppingGroupRepository extends JointShoppingGroupRepository, JpaRepository<JointShoppingGroup, Long> {
}
