package com.pettory.userserver.walkingRecord.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class WalkingRecordDailyResponse {
    private Long walkingRecordId;
    private LocalDate walkingRecordDate;
    private Long walkingRecordDuration;
    private String userNickname;
}
