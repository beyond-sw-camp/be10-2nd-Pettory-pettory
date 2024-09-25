package com.pettory.pettory.jointshopping.command.application.service;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupCreateRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupUpdateRequest;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingGroup;
import com.pettory.pettory.jointshopping.command.domain.service.JointShoppingGroupDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class JointShoppingGroupApplicationService {

    private final JointShoppingGroupDomainService jointShoppingGroupDomainService;

    /* 공동구매모임 등록 */
    @Transactional
    public Long createGroup(JointShoppingGroupCreateRequest groupRequest, MultipartFile productImg) {

        /* jointshoppinggroup 도메인 로직 실행, entity 반환 */
        JointShoppingGroup newJointShoppingGroup = jointShoppingGroupDomainService.createGroup(groupRequest, productImg);

        /* save 로직 실행 */
        JointShoppingGroup jointShoppingGroup = jointShoppingGroupDomainService.saveGroup(newJointShoppingGroup);

        /* 등록된 번호 반환 */
        return jointShoppingGroup.getJointShoppingGroupNum();
    }

    /* 공동구매모임 수정 */
    @Transactional
    public void updateGroup(Long jointShoppingGroupNum, JointShoppingGroupUpdateRequest productRequest, MultipartFile productImg) {
        jointShoppingGroupDomainService.updateGroup(jointShoppingGroupNum, productRequest, productImg);
    }

    /* 공동구매모임 삭제 */
    @Transactional
    public void deleteGroup(Long jointShoppingGroupNum) {
        jointShoppingGroupDomainService.deleteGroup(jointShoppingGroupNum);
    }
}
