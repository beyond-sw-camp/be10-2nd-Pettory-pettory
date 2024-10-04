package com.pettory.pettory.jointshopping.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@Schema(description = "공동구매모임 참가자 배송 정보 응답")
public class JointShoppingParticipationDeliveryInfoResponse {
    private String userCourierCode;
    private String userInvoiceNum;
    private Boolean productsReceiptYn;
}
