package com.pettory.pettory.jointshopping.query.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingGroupDTO;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingGroupDeliveryInfoResponse;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingParticipationDeliveryInfoResponse;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingUserListResponse;
import com.pettory.pettory.jointshopping.query.mapper.JointShoppingGroupMapper;
import com.pettory.pettory.jointshopping.query.mapper.JointShoppingParticipationMapper;
import com.pettory.pettory.user.query.dto.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JointShoppingParticipationQueryService {

    private final JointShoppingParticipationMapper jointShoppingParticipationMapper;

    /* 현재 공동구매모임 참가의 전체 사용자 목록 조회 */
    @Transactional(readOnly = true)
    public JointShoppingUserListResponse getParticipants(Integer page, Integer size, Long groupNum) {
        int offset = (page - 1) * size;
        List<UserInfoResponse> Users = jointShoppingParticipationMapper.selectParticipants(offset, size, groupNum);

        long totalItems = jointShoppingParticipationMapper.countParticipants(groupNum);

        return JointShoppingUserListResponse.builder()    // 이 클래스가 가지고 있는 필드값들이 메서드에 자동완성, 세팅을 여기서 함
                .groupUserList(Users)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();
    }

    /*  공동구매 물품 배송 정보 조회(참가자) */
    @Transactional(readOnly = true)
    public JointShoppingParticipationDeliveryInfoResponse getDeliveryInfo(Long participationNum) {
        JointShoppingParticipationDeliveryInfoResponse jointShoppingParticipationDeliveryInfoResponse
                = jointShoppingParticipationMapper.selectParticipantByNum(participationNum);

        if (jointShoppingParticipationDeliveryInfoResponse == null) {
            throw new NotFoundException(
                    "해당 코드를 가진 참가자을 찾지 못했습니다. 참가자 코드 : " + participationNum);
        }

        return JointShoppingParticipationDeliveryInfoResponse.builder()
                .userCourierCode(jointShoppingParticipationDeliveryInfoResponse.getUserCourierCode())
                .userInvoiceNum(jointShoppingParticipationDeliveryInfoResponse.getUserInvoiceNum())
                .productsReceiptYn(jointShoppingParticipationDeliveryInfoResponse.getProductsReceiptYn())
                .build();
    }

}
