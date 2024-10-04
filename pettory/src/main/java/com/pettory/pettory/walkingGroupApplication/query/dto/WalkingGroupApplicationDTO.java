package com.pettory.pettory.walkingGroupApplication.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
// 쿼리문에서 보여지고 싶은 select 문 작성
@Getter
@Setter
@Schema(description = "산책 모임 신청 DTO")
public class WalkingGroupApplicationDTO {
    private int walkingGroupId;
    private int walkingGroupApplicationId;
    private String walkingGroupName;
    private String userName;
    private String walkingGroupApprovalState;
    private LocalDateTime walkingGroupApplicationInsertDatetime;
}
