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
}
