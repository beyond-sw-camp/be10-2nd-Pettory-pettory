package com.pettory.mainserver.jointshopping.command.domain.repository;

import com.pettory.mainserver.jointshopping.command.domain.aggregate.JointShoppingGroup;

import java.util.Optional;

public interface JointShoppingGroupRepository {

    JointShoppingGroup save(JointShoppingGroup jointShoppingGroup);

    Optional<JointShoppingGroup> findById(Long jointShoppingGroupNum);

    void deleteById(Long jointShoppingGroupNum);
}
