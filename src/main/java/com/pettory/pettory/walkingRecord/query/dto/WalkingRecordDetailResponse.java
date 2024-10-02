package com.pettory.pettory.walkingRecord.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class WalkingRecordDetailResponse {
    private Long walkingRecordId;
    private LocalDate walkingRecordDate;
    private Long walkingRecordDuration;
    private Long walkingRecordPoopCount;
    private String walkingRecordWaterAmount;
    private String walkingRecordMemo;
    private String userNickname;
}
