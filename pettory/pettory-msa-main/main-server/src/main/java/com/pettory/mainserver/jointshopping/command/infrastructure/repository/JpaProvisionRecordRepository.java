package com.pettory.mainserver.jointshopping.command.infrastructure.repository;

import com.pettory.mainserver.jointshopping.command.domain.aggregate.Bookmark;
import com.pettory.mainserver.jointshopping.command.domain.aggregate.ProvisionRecord;
import com.pettory.mainserver.jointshopping.command.domain.repository.ProvisionRecordRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProvisionRecordRepository extends ProvisionRecordRepository, JpaRepository<ProvisionRecord, Long> {
}
