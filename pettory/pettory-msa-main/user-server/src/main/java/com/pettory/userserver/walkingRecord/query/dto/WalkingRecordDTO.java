package com.pettory.userserver.walkingRecord.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WalkingRecordDTO {
    private LocalDate walkingRecordDate;
    private Long walkingRecordDuration;
    private Long walkingRecordPoopCount;
    private String walkingRecordWaterAmount;
    private String walkingRecordMemo;

}
