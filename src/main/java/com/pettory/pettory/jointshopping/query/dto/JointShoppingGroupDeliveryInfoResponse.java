package com.pettory.pettory.jointshopping.query.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JointShoppingGroupDeliveryInfoResponse {
    private String hostCourierCode;
    private String hostInvoiceNum;
}
