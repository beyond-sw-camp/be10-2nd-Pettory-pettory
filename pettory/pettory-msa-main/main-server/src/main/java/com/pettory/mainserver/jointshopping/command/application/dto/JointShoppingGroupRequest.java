package com.pettory.mainserver.jointshopping.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor    // 모든 값을 전달받은 생성자 생성
@Schema(description = "공동구매모임 요청")
public class JointShoppingGroupRequest {

    @Schema(example = "모집방입다")
    @NotBlank
    private final String jointShoppingGroupName;

    @Schema(example = "강아지용청소기")
    @NotBlank
    private final String jointShoppingProducts;

    @Schema(example = "청소기살사람")
    private final String jointShoppingInfo;

    @Schema(example = "100000")
    @NotNull
    private final Integer jointShoppingCost;

    @Schema(example = "100")
    @NotNull
    private final Integer jointShoppingGroupMaximumCount;

    @Schema(example = "10")
    @NotNull
    private final Integer jointShoppingParticipationMaximumCount;

    private final String hostCourierCode;

    private final String hostInvoiceNum;

    @Schema(example = "5")
    @NotNull
    private final Long jointShoppingCategoryNum;

    @Schema(example = "3")
    @NotNull
    private final Long userId;

}
