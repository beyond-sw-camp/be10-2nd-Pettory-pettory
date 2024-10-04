package com.pettory.pettory.chat.dto.chatroom;

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
@Schema(description = "등록할 채팅방 DTO 정보")
public class InsertChatRoomDTO {
    @Schema(description = "채팅방 고유번호")
    private int chatroomUniqueNum;

    @Schema(description = "채팅방 등록 일시")
    private LocalDateTime chatroomInsertTime;

    @Schema(description = "채팅방 상태")
    private String chatroomState;

    @Schema(description = "채팅방 타입 (WALKING, JOINTSHOP)")
    private String chatroomType;

    @Schema(description = "산책모임방, 공동구매 방 번호")
    private int chatroomTypeNum;
}
