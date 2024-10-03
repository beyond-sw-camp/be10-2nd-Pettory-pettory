package com.pettory.pettory.walkingGroupApplication.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "가입한 산책 모임 DTO")
public class RegisterWalkingGroupDTO {
    private int registerWalkingGroupId;
    private int walkingGroupId;
    private int userId;
    private String userName;
    private String registerWalkingGroupState;
}
