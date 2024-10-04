package com.pettory.mainserver.jointshopping.command.domain.service;

import com.pettory.mainserver.exception.NotFoundException;
import com.pettory.mainserver.jointshopping.command.application.dto.JointShoppingDeliveryInfoRequest;
import com.pettory.mainserver.jointshopping.command.application.dto.JointShoppingParticipationRequest;
import com.pettory.mainserver.jointshopping.command.domain.aggregate.JointShoppingParticipationUser;
import com.pettory.mainserver.jointshopping.command.domain.repository.JointShoppingParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JointShoppingParticipationDomainService {

    private final JointShoppingParticipationRepository jointShoppingParticipationRepository;
    private final ModelMapper modelMapper;

    /* 도메인 객체를 생성하는 로직 */
    public JointShoppingParticipationUser createParticipation(JointShoppingParticipationRequest participationRequest) {
        /* entity 생성 */
        JointShoppingParticipationUser newJointShoppingParticipationUser
                = modelMapper.map(participationRequest, JointShoppingParticipationUser.class);

        return newJointShoppingParticipationUser;
    }

    /* 도메인 객체를 저장하는 로직 */
    public JointShoppingParticipationUser saveParticipation(JointShoppingParticipationUser newJointShoppingParticipationUser) {
        return jointShoppingParticipationRepository.save(newJointShoppingParticipationUser);
    }

    /* 도메인 객체를 삭제하는 로직 */
    public void deleteParticipation(Long participationNum) {
        jointShoppingParticipationRepository.deleteById(participationNum);
    }

    /* 배송 정보를 수정하는 로직 */
    public void updateDeliveryInfo(Long participationNum, JointShoppingDeliveryInfoRequest deliveryInfoRequest) {
        JointShoppingParticipationUser jointShoppingParticipationUser
                = jointShoppingParticipationRepository.findById(participationNum)
                .orElseThrow(() -> new NotFoundException("해당 번호에 맞는 참가자가 없습니다. code : " + participationNum));

        /* 수정을 위해 엔터티 정보 변경 */
        jointShoppingParticipationUser.update(
                deliveryInfoRequest.getCourierCode(),
                deliveryInfoRequest.getInvoiceNum()
        );
    }

    /* 물품 수령 여부를 수정하는 로직 */
    public void updateProductsReceipt(Long participationNum) {
        JointShoppingParticipationUser jointShoppingParticipationUser
                = jointShoppingParticipationRepository.findById(participationNum)
                .orElseThrow(() -> new NotFoundException("해당 번호에 맞는 참가자가 없습니다. code : " + participationNum));

        /* 수정을 위해 엔터티 정보 변경 */
        jointShoppingParticipationUser.changeProductsReceipt();
    }

    /* 현재 참가자 수를 반환하는 로직 */
    public Integer findUserCount(Long jointShoppingGroupNum) {
        List<JointShoppingParticipationUser> jointShoppingParticipationUserList = jointShoppingParticipationRepository.findByJointShoppingGroupNum(jointShoppingGroupNum);

        return jointShoppingParticipationUserList.size();
    }

    /* 물품 수령한 참가자 수를 반환하는 로직 */
    public Integer findReceiptUserCount(Long jointShoppingGroupNum) {
        List<JointShoppingParticipationUser> jointShoppingParticipationUserList = jointShoppingParticipationRepository.findByProductsReceiptYnTrue();

        return jointShoppingParticipationUserList.size();
    }

    /* 가입함 모임 찾기 */
    public Long findGroup(Long participationNum) {
        JointShoppingParticipationUser jointShoppingParticipationUser
                = jointShoppingParticipationRepository.findById(participationNum)
                .orElseThrow(() -> new NotFoundException("해당 번호에 맞는 참가자가 없습니다. code : " + participationNum));

        return jointShoppingParticipationUser.getJointShoppingGroupNum();
    }


}
