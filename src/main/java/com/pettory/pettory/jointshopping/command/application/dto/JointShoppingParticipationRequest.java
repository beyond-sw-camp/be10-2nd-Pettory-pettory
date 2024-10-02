package com.pettory.pettory.jointshopping.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "공동구매모임참가 요청")
public class JointShoppingParticipationRequest {

    @NotNull
    private final Integer paymentCost;
    private final String userCourierCode;
    private final String userInvoiceNum;
    @NotNull
    private final Long jointShoppingGroupNum;
    @NotNull
    private final Long userId;

}
