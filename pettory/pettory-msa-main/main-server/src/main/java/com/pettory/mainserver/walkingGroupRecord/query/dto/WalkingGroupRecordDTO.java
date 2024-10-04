package com.pettory.mainserver.walkingGroupRecord.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WalkingGroupRecordDTO {
    private int walkingGroupRecordId;
    private int walkingGroupId;
    private LocalDate walkingGroupDate;
    private int walkingGroupRecordDuration;
    private String walkingGroupRouteStart;
    private String walkingGroupRouteEnd;
    private String walkingGroupRecordState;
}
