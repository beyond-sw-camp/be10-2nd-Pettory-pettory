package com.pettory.mainserver.jointshopping.command.infrastructure.repository;


import com.pettory.mainserver.jointshopping.command.domain.aggregate.JointShoppingGroupUser;
import com.pettory.mainserver.jointshopping.command.domain.repository.JointShoppingGroupUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJointShoppingGroupUserRepository extends JointShoppingGroupUserRepository, JpaRepository<JointShoppingGroupUser, Long> {
}
