package com.pettory.pettory.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/* 채팅 Entity 생성 */
@Entity
@Getter
@Table(name="chatting_table")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chatting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chattingUniqueNum;
    private int chattingChatroomUniqueNum;
    private String chattingContent;
    private LocalDateTime chattingInsertTime;
    private LocalDateTime chattingUpdateTime;
    private LocalDateTime chattingDeleteTime;
    private String chattingState;
    private int userId;

    /* 채팅 수정 기능
    * 1) chattingContent : 수정할 채팅 내용
    * 2) updateTime : 채팅 수정 일시
    * 3) chattingState : 채팅 상태
    * */
    public void modifyChatting(String chattingContent, LocalDateTime updateTime, String chattingState) {
      this.chattingContent = chattingContent;
      this.chattingUpdateTime = updateTime;
      this.chattingState = chattingState;
    }

    /* 채팅 얇은 삭제 기능
    * 1) chattingState : 채팅 상태
    * 2) deleteTime : 삭제 시간
    * */
    public void softDeleteChatting(String chattingState, LocalDateTime deleteTime) {
        this.chattingState = chattingState;
        this.chattingDeleteTime = deleteTime;
    }
}
