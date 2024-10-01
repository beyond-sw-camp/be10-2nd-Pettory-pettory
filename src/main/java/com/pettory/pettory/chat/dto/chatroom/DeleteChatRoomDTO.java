package com.pettory.pettory.chat.dto.chatroom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class DeleteChatRoomDTO {
    private int chatroomUniqueNum;
    private LocalDateTime chatroomUpdateTime;
    private LocalDateTime chatroomDeleteTime;
    private String chatroomState;

    public DeleteChatRoomDTO(int chatroomUniqueNum) {
        this.chatroomUniqueNum = chatroomUniqueNum;
    }
}
