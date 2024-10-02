package com.pettory.pettory.jointshopping.command.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor    // 모든 값을 전달받은 생성자 생성
public class JointShoppingDeliveryInfoRequest {

    @NotEmpty
    private final String courierCode;
    @NotEmpty
    private final String invoiceNum;
    
}
