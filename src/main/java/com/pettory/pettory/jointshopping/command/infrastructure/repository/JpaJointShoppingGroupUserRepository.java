package com.pettory.pettory.jointshopping.command.infrastructure.repository;


import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingGroupUser;
import com.pettory.pettory.jointshopping.command.domain.repository.JointShoppingGroupUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJointShoppingGroupUserRepository extends JointShoppingGroupUserRepository, JpaRepository<JointShoppingGroupUser, Long> {
}
