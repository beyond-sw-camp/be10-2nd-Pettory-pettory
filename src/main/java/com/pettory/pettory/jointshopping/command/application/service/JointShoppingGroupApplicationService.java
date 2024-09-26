package com.pettory.pettory.jointshopping.command.application.service;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupUserRequest;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingGroup;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingGroupUser;
import com.pettory.pettory.jointshopping.command.domain.service.JointShoppingGroupDomainService;
import com.pettory.pettory.jointshopping.command.domain.service.JointShoppingGroupUserDomainService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class JointShoppingGroupApplicationService {

    private final JointShoppingGroupDomainService jointShoppingGroupDomainService;
    private final JointShoppingGroupUserDomainService jointShoppingGroupUserDomainService;

    /* 공동구매모임 등록 */
    @Transactional
    public Long createGroup(JointShoppingGroupRequest groupRequest, MultipartFile productImg) {

        /* jointshoppinggroup 도메인 생성 로직 실행, entity 반환 */
        JointShoppingGroup newJointShoppingGroup = jointShoppingGroupDomainService.createGroup(groupRequest, productImg);

        /* save 로직 실행 */
        JointShoppingGroup jointShoppingGroup = jointShoppingGroupDomainService.saveGroup(newJointShoppingGroup);

        /* 등록된 번호 반환 */
        return jointShoppingGroup.getJointShoppingGroupNum();
    }

    /* 공동구매모임 수정 */
    @Transactional
    public void updateGroup(Long jointShoppingGroupNum, JointShoppingGroupRequest productRequest, MultipartFile productImg) {
        jointShoppingGroupDomainService.updateGroup(jointShoppingGroupNum, productRequest, productImg);
    }

    /* 공동구매모임 삭제 */
    @Transactional
    public void deleteGroup(Long jointShoppingGroupNum) {
        jointShoppingGroupDomainService.deleteGroup(jointShoppingGroupNum);
    }

    /* 공동구매모임 참가(모임 사용자 등록) */
    @Transactional
    public Long insertGroupUser(JointShoppingGroupUserRequest groupUserRequest) {

        /* JointShoppingGroupUser 도메인 생성 로직 실행, entity 반환 */
        JointShoppingGroupUser newJointShoppingGroupUser = jointShoppingGroupUserDomainService.createGroupUser(groupUserRequest);

        /* save 로직 실행 */
        JointShoppingGroupUser jointShoppingGroupUser = jointShoppingGroupUserDomainService.saveGroupUser(newJointShoppingGroupUser);

        /* 등록된 번호 반환 */
        return jointShoppingGroupUser.getJointShoppingGroupUserListNum();
    }

    /* 공동구매모임 나가기(모임 사용자 삭제) */
    @Transactional
    public void exitGroupUser(Long jointShoppingGroupUserNum) {
        jointShoppingGroupUserDomainService.deleteGroupUser(jointShoppingGroupUserNum);
    }

    /* 공동구매모임 강퇴 (모임 사용자 삭제, 재등록 불가 ) */
    @Transactional
    public void withdrawalGroupUser(Long jointShoppingGroupUserNum) {
        // 강퇴 여부 수정
        JointShoppingGroupUser newJointShoppingGroupUser = jointShoppingGroupUserDomainService.updateResignYn(jointShoppingGroupUserNum);

        /* saveAndFlush 로직 실행 */
        jointShoppingGroupUserDomainService.saveAndFlushGroupUser(newJointShoppingGroupUser);

        /* soft delete */
        jointShoppingGroupUserDomainService.deleteGroupUser(jointShoppingGroupUserNum);
    }
}
