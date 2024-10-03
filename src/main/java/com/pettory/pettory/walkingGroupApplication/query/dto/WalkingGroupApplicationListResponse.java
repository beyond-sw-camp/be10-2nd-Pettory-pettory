package com.pettory.pettory.walkingGroupApplication.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Schema(description = "산책 모임 신청 리스트 정보")
public class WalkingGroupApplicationListResponse {
    private List<WalkingGroupApplicationDTO> walkingGroupApplications;
    private int currentPage;
    private int totalPages;
    private long totalItems;
}
