package com.pettory.pettory.jointshopping.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor    // 모든 값을 전달받은 생성자 생성
@Schema(description = "배송 정보 요청")
public class JointShoppingDeliveryInfoRequest {

    @Schema(example = "03")
    @NotEmpty
    private final String courierCode;

    @Schema(example = "22222222")
    @NotEmpty
    private final String invoiceNum;
    
}
