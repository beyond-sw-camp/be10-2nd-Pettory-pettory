package com.pettory.pettory.jointshopping.service;


import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingDeliveryInfoRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingParticipationRequest;
import com.pettory.pettory.jointshopping.command.application.service.JointShoppingParticipationApplicationService;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingParticipationDeliveryInfoResponse;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingUserListResponse;
import com.pettory.pettory.jointshopping.query.dto.ProvisionRecordResponse;
import com.pettory.pettory.jointshopping.query.service.JointShoppingParticipationQueryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class JointShoppingParticipationServiceTest {

    @Autowired
    private JointShoppingParticipationApplicationService jointShoppingParticipationApplicationService;
    @Autowired
    private JointShoppingParticipationQueryService jointShoppingParticipationQueryService;


    private static Stream<Arguments> getCreateParticipation() {
        return Stream.of(
                Arguments.of(
                        10000,
                        null,
                        null,
                        7L,
                        5L
                )
        );
    }

    private static Stream<Arguments> getUpdateDeliveryInfo() {
        return Stream.of(
                Arguments.of(
                        5L,
                        "22",
                        "333333333"
                )
        );
    }

    private static Stream<Arguments> getParticipants() {
        return Stream.of(
                Arguments.of(
                        1,
                        5,
                        7L
                )
        );
    }

    /* 공동구매 참가 등록 */
    @ParameterizedTest
    @MethodSource("getCreateParticipation")
    void testCreateGroup(
            Integer paymentCost, String userCourierCode, String userInvoiceNum,
            Long jointShoppingGroupNum, Long userId
    ) {

        JointShoppingParticipationRequest jointShoppingParticipationRequest = new JointShoppingParticipationRequest(
                paymentCost,
                userCourierCode,
                userInvoiceNum,
                jointShoppingGroupNum,
                userId
        );

        Assertions.assertDoesNotThrow(
                () -> jointShoppingParticipationApplicationService.createParticipation(jointShoppingParticipationRequest)
        );
    }

    /* 공동구매 참가 취소 */
    @Test
    void testDeleteParticipation() {
        Assertions.assertDoesNotThrow(
                () -> jointShoppingParticipationApplicationService.deleteParticipation(6L)
        );
    }

    /* 공동구매 참가자 물품 배송 정보 등록(수정) */
    @ParameterizedTest
    @MethodSource("getUpdateDeliveryInfo")
    void testUpdateDeliveryInfo(
            Long participationNum, String courierCode, String invoiceNum
    ) {

        JointShoppingDeliveryInfoRequest jointShoppingDeliveryInfoRequest
                = new JointShoppingDeliveryInfoRequest(
                courierCode, invoiceNum
        );

        Assertions.assertDoesNotThrow(
                () -> jointShoppingParticipationApplicationService.updateDeliveryInfo(participationNum, jointShoppingDeliveryInfoRequest)
        );
    }

    /*  공동구매 참가자 물품 수령으로 변경 */
    @Test
    void testUpdateProductsReceipt() {
        Assertions.assertDoesNotThrow(
                () -> jointShoppingParticipationApplicationService.updateProductsReceipt(5L)
        );
    }

    /* 현재 공동구매모임 참가의 전체 사용자 목록 조회 */
    @ParameterizedTest
    @MethodSource("getParticipants")
    void testGetParticipants(Integer page, Integer size, Long groupNum) {
        Assertions.assertDoesNotThrow(
                () -> {
                    JointShoppingUserListResponse response = jointShoppingParticipationQueryService.getParticipants(page, size, groupNum);
                    response.getGroupUserList().forEach(groupUser -> System.out.println(groupUser));
                }
        );
    }

    /*  공동구매 물품 배송 정보 조회(참가자) */
    @Test
    void testGetDeliveryInfo() {
        Assertions.assertDoesNotThrow(
                () -> {
                    JointShoppingParticipationDeliveryInfoResponse response = jointShoppingParticipationQueryService.getDeliveryInfo(5L);
                    System.out.println(response);
                }
        );
    }
}

