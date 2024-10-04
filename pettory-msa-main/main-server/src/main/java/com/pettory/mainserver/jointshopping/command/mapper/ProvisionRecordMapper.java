package com.pettory.mainserver.jointshopping.command.mapper;

import com.pettory.mainserver.jointshopping.command.domain.aggregate.ProvisionRecord;

public class ProvisionRecordMapper {
    public static ProvisionRecord toEntity(Long jointShoppingGroupNum, Integer provisionCost) {
        return ProvisionRecord.create(provisionCost, jointShoppingGroupNum);
    }
}
