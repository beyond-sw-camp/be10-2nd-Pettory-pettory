package com.pettory.mainserver.walkingGroupApplication.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
// 쿼리문에서 보여지고 싶은 select 문 작성
@Getter
@Setter
public class WalkingGroupApplicationDTO {
    private int walkingGroupId;
    private int walkingGroupApplicationId;
    private String walkingGroupName;
    private String userName;
    private String walkingGroupApprovalState;
    private LocalDateTime walkingGroupApplicationInsertDatetime;
}
