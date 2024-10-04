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
@Schema(description = "삭제할 채팅방 DTO 정보")
public class DeleteChatRoomDTO {
    @Schema(description = "채팅방 고유번호")
    private int chatroomUniqueNum;

    @Schema(description = "채팅방 업데이트 일시")
    private LocalDateTime chatroomUpdateTime;

    @Schema(description = "채팅방 삭제 일시")
    private LocalDateTime chatroomDeleteTime;

    @Schema(description = "채팅방 상태")
    private String chatroomState;

    public DeleteChatRoomDTO(int chatroomUniqueNum) {
        this.chatroomUniqueNum = chatroomUniqueNum;
    }
}
