package com.pettory.pettory.walkingGroupRecord.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "산책 모임 기록 상세 정보")
public class WalkingGroupRecordDetailResponse {

    private List<WalkingGroupRecordDTO> walkingGroupRecord;

    public WalkingGroupRecordDetailResponse(List<WalkingGroupRecordDTO> walkingGroupRecord) {
        this.walkingGroupRecord = walkingGroupRecord;
    }

}
