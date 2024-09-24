package com.pettory.pettory.jointshopping.command.application.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor    // 모든 값을 전달받은 생성자 생성
public class jointShoppingGroupCreateRequest {

    @NotBlank
    private final String jointShoppingGroupName;
    @NotBlank
    private final String jointShoppingProducts;
    private final String jointShoppingInfo;
    @NotNull
    private final Integer jointShoppingCost;
    @NotNull
    private final Integer jointShoppingGroupMaximumCount;
    @NotNull
    private final Integer jointShoppingParticipationMaximumCount;
    private final String hostInvoiceNum;
    @NotNull
    private final Long jointShoppingCategoryNum;
    @NotNull
    private final Long userId;

}
