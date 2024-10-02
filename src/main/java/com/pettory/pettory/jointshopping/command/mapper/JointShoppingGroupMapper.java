package com.pettory.pettory.jointshopping.command.mapper;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupRequest;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingGroup;

public class JointShoppingGroupMapper {

    public static JointShoppingGroup toEntity(JointShoppingGroupRequest groupRequest, String imageDir) {
        return JointShoppingGroup.create(
                groupRequest.getJointShoppingGroupName(),
                groupRequest.getJointShoppingProducts(),
                imageDir,
                groupRequest.getJointShoppingInfo(),
                groupRequest.getJointShoppingCost(),
                groupRequest.getJointShoppingGroupMaximumCount(),
                groupRequest.getJointShoppingParticipationMaximumCount(),
                groupRequest.getHostInvoiceNum(),
                groupRequest.getJointShoppingCategoryNum(),
                groupRequest.getUserId()
        );
    }
}
