package com.pettory.pettory.jointshopping.command.infrastructure.repository;

import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingCategory;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingGroup;
import com.pettory.pettory.jointshopping.command.domain.repository.JointShoppingCategoryRepository;
import com.pettory.pettory.jointshopping.command.domain.repository.JointShoppingGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJointShoppingCategoryRepository extends JointShoppingCategoryRepository, JpaRepository<JointShoppingCategory, Long> {
}
