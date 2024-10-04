package com.pettory.userserver.family.query.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReceivedInvitationResponse {
    private String userName;
    private String familyName;
}
