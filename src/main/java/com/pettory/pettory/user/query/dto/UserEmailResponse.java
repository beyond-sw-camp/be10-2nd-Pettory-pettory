package com.pettory.pettory.user.query.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserEmailResponse {
    private Long userId;
    private String userEmail;
}
