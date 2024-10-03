package com.pettory.pettory.walkingGroupApplication.query.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Schema(description = "가입한 산책 모임 리스트 정보")
public class RegisterWalkingGroupListResponse {
    private List<RegisterWalkingGroupDTO> registerWalkingGroups;
    private int currentPage;
    private int totalPages;
    private long totalItems;
}
