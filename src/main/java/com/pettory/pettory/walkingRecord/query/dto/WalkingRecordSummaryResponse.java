package com.pettory.pettory.walkingRecord.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class WalkingRecordSummaryResponse {
    private LocalDate walkingRecordDate;    // 산책 날짜
    private Long walkingRecordDuration; // 산책 시간
}
