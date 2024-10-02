package com.pettory.pettory.walkinggroup.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class WalkingGroupListResponse {
    private List<WalkingGroupDTO> walkingGroups;
    private int currentPage;    // 현재 페이지
    private int totalPages;     // 전체 페이지 수
    private long totalItems;    // 총 아이템 수
}
