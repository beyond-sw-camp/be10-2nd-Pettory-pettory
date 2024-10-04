package com.pettory.mainserver.walkingGroupApplication.query.dto;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RegisterWalkingGroupListResponse {
    private List<RegisterWalkingGroupDTO> registerWalkingGroups;
    private int currentPage;
    private int totalPages;
    private long totalItems;
}
