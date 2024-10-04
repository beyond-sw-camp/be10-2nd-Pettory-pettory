package com.pettory.pettory.walkingRecord.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class WalkingRecordCreateRequest {

    @Schema(example = "7")
    @NotNull
    private Long petId;

    @Schema(example = "2024-10-02")
    @NotNull
    private LocalDate walkingRecordDate;

    @Schema(example = "60")
    private Long walkingRecordDuration;

    @Schema(example = "1")
    private Long walkingRecordPoopCount;

    @Schema(example = "한 모금")
    private String walkingRecordWaterAmount;

    @Schema(example = "절친 다롱이 만남")
    private String walkingRecordMemo;

}
