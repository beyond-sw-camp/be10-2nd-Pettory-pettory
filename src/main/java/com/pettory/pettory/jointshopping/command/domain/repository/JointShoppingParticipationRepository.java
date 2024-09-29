package com.pettory.pettory.jointshopping.command.domain.repository;

import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingParticipationUser;

public interface JointShoppingParticipationRepository {
    JointShoppingParticipationUser save(JointShoppingParticipationUser newJointShoppingParticipationUser);

    void deleteById(Long participationNum);
}
