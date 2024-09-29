package com.pettory.pettory.jointshopping.command.application.service;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingParticipationRequest;
import com.pettory.pettory.jointshopping.command.domain.aggregate.Bookmark;
import com.pettory.pettory.jointshopping.command.domain.aggregate.JointShoppingParticipationUser;
import com.pettory.pettory.jointshopping.command.domain.service.JointShoppingParticipationDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JointShoppingParticipationApplicationService {

    private final JointShoppingParticipationDomainService jointShoppingParticipationDomainService;

    /* 공동구매 참가 등록 */
    @Transactional
    public Long createParticipation(JointShoppingParticipationRequest participationRequest) {

        /*결제 기능을 여기에 추가*/

        /* Bookmark 도메인 로직 실행, entity 반환 */
        JointShoppingParticipationUser newJointShoppingParticipationUser
                = jointShoppingParticipationDomainService.createParticipation(participationRequest);

        /* save 로직 실행 */
        JointShoppingParticipationUser jointShoppingParticipationUser
                = jointShoppingParticipationDomainService.saveParticipation(newJointShoppingParticipationUser);

        /* 등록된 번호 반환 */
        return jointShoppingParticipationUser.getJointShoppingParticipationUserListNum();

    }

    /* 공동구매 참가 취소 */
    @Transactional
    public void deleteParticipation(Long participationNum) {
        jointShoppingParticipationDomainService.deleteParticipation(participationNum);
    }
}
