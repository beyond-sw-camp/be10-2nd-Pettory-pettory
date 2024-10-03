package com.pettory.pettory.walkingGroupApplication.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class WalkingGroupApplicationListResponse {
    private List<WalkingGroupApplicationDTO> walkingGroupApplications;
    private int currentPage;
    private int totalPages;
    private long totalItems;
}
