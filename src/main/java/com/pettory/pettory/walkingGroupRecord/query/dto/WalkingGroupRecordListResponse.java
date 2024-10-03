package com.pettory.pettory.walkingGroupRecord.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Schema(description = "산책 모임 기록 리스트 정보")
public class WalkingGroupRecordListResponse {
    private List<WalkingGroupRecordDTO> walkingGroupRecords;
    private int currentPage;
    private int totalPages;
    private long totalItems;
}
