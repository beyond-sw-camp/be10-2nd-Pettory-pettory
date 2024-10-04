package com.pettory.pettory.jointshopping.command.infrastructure.repository;

import com.pettory.pettory.jointshopping.command.domain.aggregate.Bookmark;
import com.pettory.pettory.jointshopping.command.domain.aggregate.ProvisionRecord;
import com.pettory.pettory.jointshopping.command.domain.repository.ProvisionRecordRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProvisionRecordRepository extends ProvisionRecordRepository, JpaRepository<ProvisionRecord, Long> {
}
