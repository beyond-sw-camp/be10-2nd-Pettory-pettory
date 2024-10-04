package com.pettory.mainserver.chat.dto.chatting;

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
@Schema(description = "얇은 삭제할 채팅 DTO")
public class SoftDeleteChattingDTO {
    @Schema(description = "채팅 고유번호")
    private int chattingUniqueNum;

    @Schema(description = "채팅방 고유번호 (FK)")
    private int chattingChatroomUniqueNum;

    @Schema(description = "채팅 삭제일시")
    private LocalDateTime chattingDeleteTime;

    @Schema(description = "채팅 상태")
    private String chattingState;

    public SoftDeleteChattingDTO() {}
}
