package com.pettory.pettory.jointshopping.query.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class JointShoppingGroupDeliveryInfoResponse {
    private String hostCourierCode;
    private String hostInvoiceNum;
}
