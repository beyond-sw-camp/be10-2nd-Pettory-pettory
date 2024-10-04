package com.pettory.mainserver.walkinggroup.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalkingGroupDTO {
    private int walkingGroupId;                         // 산책모임id
    private String walkingGroupName;                    // 산책모임이름
    private String walkingGroupInfo;                    // 산책모임설명
    private String userName;
    private int walkingGroupMaximumCount;               // 산책모임가입최대인원수
    private String walkingGroupState;                   // 산책모임상태
}
