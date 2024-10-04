package com.pettory.mainserver.jointshopping.query.dto;

import com.pettory.mainserver.user.query.dto.UserInfoResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Schema(description = "지급기록 응답")
public class ProvisionRecordResponse {
    private List<ProvisionRecordDTO> provisionRecords;
    private int currentPage;            // 현재 페이지
    private int totalPages;             // 전체 페이지 수
    private long totalItems;            // 총 아이템 수
}
