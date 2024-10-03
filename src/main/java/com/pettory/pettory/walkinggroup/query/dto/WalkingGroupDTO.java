package com.pettory.pettory.walkinggroup.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "산책모임 DTO")
public class WalkingGroupDTO {
    private int walkingGroupId;                         // 산책모임id
    private String walkingGroupName;                    // 산책모임이름
    private String walkingGroupInfo;                    // 산책모임설명
    private String userName;
    private int walkingGroupMaximumCount;               // 산책모임가입최대인원수
    private String walkingGroupState;                   // 산책모임상태
}
