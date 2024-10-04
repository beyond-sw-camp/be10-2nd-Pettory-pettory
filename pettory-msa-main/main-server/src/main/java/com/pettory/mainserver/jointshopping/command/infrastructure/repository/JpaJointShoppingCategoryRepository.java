package com.pettory.mainserver.jointshopping.command.infrastructure.repository;

import com.pettory.mainserver.jointshopping.command.domain.aggregate.JointShoppingCategory;
import com.pettory.mainserver.jointshopping.command.domain.aggregate.JointShoppingGroup;
import com.pettory.mainserver.jointshopping.command.domain.repository.JointShoppingCategoryRepository;
import com.pettory.mainserver.jointshopping.command.domain.repository.JointShoppingGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJointShoppingCategoryRepository extends JointShoppingCategoryRepository, JpaRepository<JointShoppingCategory, Long> {
}
