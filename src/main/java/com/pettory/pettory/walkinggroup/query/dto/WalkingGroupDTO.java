package com.pettory.pettory.walkinggroup.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WalkingGroupDTO {
    private int walkingGroupId;                         // 산책모임id
    private String walkingGroupName;                    // 산책모임이름
    private String walkingGroupInfo;                    // 산책모임설명
    private int walkingGroupMaximumCount;               // 산책모임가입최대인원수
    private String walkingGroupState;                   // 산책모임상태
    private LocalDateTime walkingGroupInsertDateTime;       // 산책모임등록일시
    private LocalDateTime walkingGroupUpdateDateTime;       // 산책모임수정일시
    private LocalDateTime walkingGroupDeleteDateTime;       // 산책모임삭제일시
}
