package com.pettory.pettory.walkingGroupRecord.query.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class WalkingGroupRecordDetailResponse {

    private List<WalkingGroupRecordDTO> walkingGroupRecord;

    public WalkingGroupRecordDetailResponse(List<WalkingGroupRecordDTO> walkingGroupRecord) {
        this.walkingGroupRecord = walkingGroupRecord;
    }

}
