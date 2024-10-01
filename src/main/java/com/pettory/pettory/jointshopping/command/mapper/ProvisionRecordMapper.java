package com.pettory.pettory.jointshopping.command.mapper;

import com.pettory.pettory.jointshopping.command.domain.aggregate.ProvisionRecord;

public class ProvisionRecordMapper {
    public static ProvisionRecord toEntity(Long jointShoppingGroupNum, Integer provisionCost) {
        return ProvisionRecord.create(provisionCost, jointShoppingGroupNum);
    }
}
