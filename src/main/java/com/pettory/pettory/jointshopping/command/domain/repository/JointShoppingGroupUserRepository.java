package com.pettory.pettory.jointshopping.command.domain.repository;

import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingGroupUser;

import java.util.Optional;

public interface JointShoppingGroupUserRepository {
    JointShoppingGroupUser save(JointShoppingGroupUser newJointShoppingGroupUser);

    void deleteById(Long jointShoppingGroupUserNum);

    Optional<JointShoppingGroupUser> findById(Long jointShoppingGroupUserNum);

    void delete(JointShoppingGroupUser newJointShoppingGroupUser);

    JointShoppingGroupUser saveAndFlush(JointShoppingGroupUser newJointShoppingGroupUser);
}
