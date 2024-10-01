package com.pettory.pettory.jointshopping.command.application.service;

import com.pettory.pettory.exception.BadJoinException;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingDeliveryInfoRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingParticipationRequest;
import com.pettory.pettory.jointshopping.command.domain.aggregate.Bookmark;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingParticipationUser;
import com.pettory.pettory.jointshopping.command.domain.aggregate.ProvisionRecord;
import com.pettory.pettory.jointshopping.command.domain.service.JointShoppingGroupDomainService;
import com.pettory.pettory.jointshopping.command.domain.service.JointShoppingParticipationDomainService;
import com.pettory.pettory.jointshopping.command.domain.service.ProvisionRecordDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class JointShoppingParticipationApplicationService {

    private final JointShoppingGroupDomainService jointShoppingGroupDomainService;
    private final JointShoppingParticipationDomainService jointShoppingParticipationDomainService;
    private final ProvisionRecordDomainService provisionRecordDomainService;

    /* 공동구매 참가 등록 */
    @Transactional
    public Long createParticipation(JointShoppingParticipationRequest participationRequest) {

        /* 참가가 가득 찼는지 체크 */
        Long jointShoppingGroupNum = participationRequest.getJointShoppingGroupNum();
        // 최대 참가자 수를 불러옴
        int max = jointShoppingGroupDomainService.findParticipationMaximumCount(jointShoppingGroupNum);
        // 현재 참가자 수를 불러옴
        int now = jointShoppingParticipationDomainService.findUserCount(jointShoppingGroupNum);
        if (now >= max) {
            throw new BadJoinException("참가자가 많아 참가하실수 없습니다.");
        }

        /*결제 기능을 여기에 추가*/


        /* JointShoppingParticipationUser 도메인 로직 실행, entity 반환 */
        JointShoppingParticipationUser newJointShoppingParticipationUser
                = jointShoppingParticipationDomainService.createParticipation(participationRequest);

        /* save 로직 실행 */
        JointShoppingParticipationUser jointShoppingParticipationUser
                = jointShoppingParticipationDomainService.saveParticipation(newJointShoppingParticipationUser);

        /* 참가인원이 가득찰 시 */
        if (now + 1 == max) {
            /* 모임 상품 상태 변경(주문완료) */
            jointShoppingGroupDomainService.changeProductsState(jointShoppingGroupNum);

            /* 지급대기상태로 생성 */
            Integer provisionCost = max * participationRequest.getPaymentCost();
            ProvisionRecord provisionRecord
                    = provisionRecordDomainService.createProvisionRecord(jointShoppingGroupNum, provisionCost);

            /* 지급대기상태로 저장 */
            provisionRecordDomainService.saveProvisionRecord(provisionRecord);
        }


        /* 등록된 번호 반환 */
        return jointShoppingParticipationUser.getJointShoppingParticipationUserListNum();

    }

    /* 공동구매 참가 취소 */
    @Transactional
    public void deleteParticipation(Long participationNum) {
        /* 모임이 참가 모집중인 상태인지 체크*/
        // 모임 찾기
        Long jointShoppingGroupNum = jointShoppingParticipationDomainService.findGroup(participationNum);
        // 상태가 모집중이 아니면 취소 불가
        jointShoppingGroupDomainService.checkProductsStateRecruitment(jointShoppingGroupNum);

        /* 결제 환불 기능 여기에 추가*/

        /* soft delete*/
        jointShoppingParticipationDomainService.deleteParticipation(participationNum);
    }

    /* 공동구매 참가자 물품 배송 정보 등록(수정) */
    @Transactional
    public void updateDeliveryInfo(Long participationNum, JointShoppingDeliveryInfoRequest jointShoppingDeliveryInfoRequest) {
        jointShoppingParticipationDomainService.updateDeliveryInfo(participationNum, jointShoppingDeliveryInfoRequest);
    }

    /*  공동구매 참가자 물품 수령으로 변경 */
    @Transactional
    public void updateProductsReceipt(Long participationNum) {
        jointShoppingParticipationDomainService.updateProductsReceipt(participationNum);

        Long jointShoppingGroupNum = jointShoppingParticipationDomainService.findGroup(participationNum);

        /* 수령시 자동으로 모임 상품 상태 변경(도착) */
        jointShoppingGroupDomainService.changeProductsState(jointShoppingGroupNum);

        /* 공동구매 참가자 모두가 물품 수령으로 변경했는지 체크 */
        int cnt = jointShoppingParticipationDomainService.findReceiptUserCount(jointShoppingGroupNum);
        int max = jointShoppingGroupDomainService.findParticipationMaximumCount(jointShoppingGroupNum);

        /* 방장에게 전체 비용지급 */
        if (cnt >= max) {
            /* 결제기능 추가 */

            /* 지급완료 처리 후 soft delete */
            provisionRecordDomainService.deleteProvisionRecord(jointShoppingGroupNum);
            /* 공동구매모임 soft delete */
            jointShoppingGroupDomainService.deleteGroup(jointShoppingGroupNum);
        }
    }
}
