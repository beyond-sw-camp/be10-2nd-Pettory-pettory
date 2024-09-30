package com.pettory.pettory.jointshopping.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
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
