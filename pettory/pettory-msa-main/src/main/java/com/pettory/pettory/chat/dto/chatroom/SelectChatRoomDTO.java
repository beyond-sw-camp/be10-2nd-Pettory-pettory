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
@Schema(description = "조회할 채팅방 DTO 정보")
public class SelectChatRoomDTO {
    @Schema(description = "채팅방 고유번호")
    private int chatRoomUniqueNum;

    @Schema(description = "채팅방 등록일시")
    private LocalDateTime chatRoomInsertTime;

    @Schema(description = "채팅방 수정일시")
    private LocalDateTime chatRoomUpdateTime;

    @Schema(description = "채팅방 삭제일시")
    private LocalDateTime chatRoomDeleteTime;

    @Schema(description = "채팅방 상태")
    private String chatRoomState;

    @Schema(description = "채팅방 타입")
    private String chatRoomType;

    @Schema(description = "산책모임방, 공동구매 방 번호")
    private int chatRoomTypeNum;
}
