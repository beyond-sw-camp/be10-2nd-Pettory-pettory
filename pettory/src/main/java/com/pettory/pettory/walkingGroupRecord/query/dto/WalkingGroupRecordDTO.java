package com.pettory.pettory.walkingGroupRecord.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "산책 모임 기록 DTO")
public class WalkingGroupRecordDTO {
    private int walkingGroupRecordId;
    private int walkingGroupId;
    private LocalDate walkingGroupDate;
    private int walkingGroupRecordDuration;
    private String walkingGroupRouteStart;
    private String walkingGroupRouteEnd;
    private String walkingGroupRecordState;
}
