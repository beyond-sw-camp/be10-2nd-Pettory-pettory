package com.pettory.pettory.chat.dto.chatting;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Schema(description = "수정할 채팅 DTO")
public class ModifyChattingDTO {
    @Schema(description = "채팅 고유번호")
    private int chattingUniqueNum;

    @Schema(description = "채팅방 고유번호 (FK)")
    private int chattingChatroomUniqueNum;

    @Schema(description = "채팅 내용")
    private String chattingContent;

    @Schema(description = "채팅 수정 일시")
    private LocalDateTime chattingUpdateTime;

    @Schema(description = "채팅 상태")
    private String chattingState;
}
