package com.pettory.mainserver.jointshopping.command.domain.repository;

import com.pettory.mainserver.jointshopping.command.domain.aggregate.ProvisionRecord;

public interface ProvisionRecordRepository {
    ProvisionRecord save(ProvisionRecord provisionRecord);

    ProvisionRecord findByJointShoppingGroupNum(Long jointShoppingGroupNum);

    void deleteByJointShoppingGroupNum(Long jointShoppingGroupNum);
}
