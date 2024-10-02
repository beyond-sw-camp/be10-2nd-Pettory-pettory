package com.pettory.pettory.jointshopping.query.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class JointShoppingParticipationDeliveryInfoResponse {
    private String userCourierCode;
    private String userInvoiceNum;
    private Boolean productsReceiptYn;
}
