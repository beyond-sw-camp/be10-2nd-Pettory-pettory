package com.pettory.pettory.walkingGroupApplication.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterWalkingGroupDTO {
    private int registerWalkingGroupId;
    private int walkingGroupId;
    private int userId;
    private String userName;
    private String registerWalkingGroupState;
}
