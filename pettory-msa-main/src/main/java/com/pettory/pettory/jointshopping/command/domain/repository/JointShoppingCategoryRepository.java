package com.pettory.pettory.jointshopping.command.domain.repository;

import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingCategory;

import java.util.Optional;

public interface JointShoppingCategoryRepository {
    JointShoppingCategory save(JointShoppingCategory jointShoppingCategory);

    Optional<JointShoppingCategory> findById(Long jointShoppingCategoryNum);

    void deleteById(Long jointShoppingCategoryNum);
}
