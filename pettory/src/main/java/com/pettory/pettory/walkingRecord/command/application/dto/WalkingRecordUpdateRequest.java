package com.pettory.pettory.walkingRecord.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class WalkingRecordUpdateRequest {
    @Schema(example = "null")
    private Long petId;

    @Schema(example = "null")
    private LocalDate walkingRecordDate;

    @Schema(example = "null")
    private Long walkingRecordDuration;

    @Schema(example = "null")
    private Long walkingRecordPoopCount;

    @Schema(example = "null")
    private String walkingRecordWaterAmount;

    @Schema(example = "짱친 다롱이 만남!!")
    private String walkingRecordMemo;

}
