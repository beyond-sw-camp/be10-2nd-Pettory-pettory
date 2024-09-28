package com.pettory.pettory.family.command.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class KickMemberRequest {
    @NotNull(message = "삭제할 회원 id를 입력하세요")
    private Long memberId;
}
