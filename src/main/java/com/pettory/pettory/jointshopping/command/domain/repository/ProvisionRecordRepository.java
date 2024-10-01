package com.pettory.pettory.jointshopping.command.domain.repository;

import com.pettory.pettory.jointshopping.command.domain.aggregate.ProvisionRecord;

public interface ProvisionRecordRepository {
    ProvisionRecord save(ProvisionRecord provisionRecord);

    ProvisionRecord findByJointShoppingGroupNum(Long jointShoppingGroupNum);

    void deleteByJointShoppingGroupNum(Long jointShoppingGroupNum);
}
