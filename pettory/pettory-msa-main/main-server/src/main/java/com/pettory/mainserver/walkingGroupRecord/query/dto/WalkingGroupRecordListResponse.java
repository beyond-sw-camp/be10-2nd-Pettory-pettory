package com.pettory.mainserver.walkingGroupRecord.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class WalkingGroupRecordListResponse {
    private List<WalkingGroupRecordDTO> walkingGroupRecords;
    private int currentPage;
    private int totalPages;
    private long totalItems;
}
