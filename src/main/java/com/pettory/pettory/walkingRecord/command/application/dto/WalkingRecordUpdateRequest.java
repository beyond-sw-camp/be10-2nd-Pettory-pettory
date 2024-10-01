package com.pettory.pettory.walkingRecord.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class WalkingRecordUpdateRequest {
    private Long petId;
    private LocalDate walkingRecordDate;
    private Long walkingRecordDuration;
    private Long walkingRecordPoopCount;
    private String walkingRecordWaterAmount;
    private String walkingRecordMemo;

}
