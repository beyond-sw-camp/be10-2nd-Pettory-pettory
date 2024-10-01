package com.pettory.pettory.jointshopping.command.domain.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingDeliveryInfoRequest;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingParticipationUser;
import com.pettory.pettory.jointshopping.command.domain.aggregate.ProvisionRecord;
import com.pettory.pettory.jointshopping.command.domain.repository.ProvisionRecordRepository;
import com.pettory.pettory.jointshopping.command.mapper.ProvisionRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProvisionRecordDomainService {

    private final ProvisionRecordRepository provisionRecordRepository;

    /* 도메인 객체를 생성하는 로직 */
    public ProvisionRecord createProvisionRecord(Long jointShoppingGroupNum, Integer provisionCost) {
        /* dto to entity */
        ProvisionRecord newProvisionRecord = ProvisionRecordMapper.toEntity(jointShoppingGroupNum, provisionCost);

        return newProvisionRecord;
    }

    /* 도메인 객체를 저장하는 로직 */
    public ProvisionRecord saveProvisionRecord(ProvisionRecord provisionRecord) {
        return provisionRecordRepository.save(provisionRecord);
    }

    /* 도메인 객체를 삭제하는 로직 */
    public void deleteProvisionRecord(Long jointShoppingGroupNum) {
        /* soft delete 될 수 있도록 entity에 설정함 */
        provisionRecordRepository.deleteByJointShoppingGroupNum(jointShoppingGroupNum);
    }

}
