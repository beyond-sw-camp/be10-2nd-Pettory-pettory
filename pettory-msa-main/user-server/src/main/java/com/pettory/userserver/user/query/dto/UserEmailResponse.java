package com.pettory.userserver.user.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserEmailResponse {
    @Schema(example = "2")
    private Long userId;

    @Schema(example = "ju@gmail.com")
    private String userEmail;
}
