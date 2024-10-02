package com.pettory.pettory.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pettory.pettory.chat.dto.chatting.InsertChattingDTO;
import com.pettory.pettory.chat.service.ChatService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/* WebSocket 핸들러 생성
* @Component 를 통한 Bean 등록 */
@Component
public class ChatHandler extends TextWebSocketHandler {

    private final ChatService chatService;

    /* JSON -> 객체
    * 객체 -> JSON 변환해주는 Mapper */
    private final ObjectMapper objectMapper;

    @Autowired
    public ChatHandler(ChatService chatService, ObjectMapper objectMapper) {
        this.chatService = chatService;
        this.objectMapper = objectMapper;
    }

    /* 1. 채팅방에 들어와있는 session 들을 추적할 list 생성 */
    private static final List<WebSocketSession> userList = new ArrayList<>();

    /* 2. 3개의 메소드 오버라이드
    * handleTextMessage
    * afterConnectionEstablished
    * afterConnectionClosed */
    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session,@NonNull TextMessage message) throws Exception {
        /* getPayload : 전송되는 데이터를 뜻함. */
        String payload = message.getPayload();
        System.out.println(payload);

        /* 채팅방에 들어와있는 모든 Session 에게 메시지 전달 */
        for(WebSocketSession sessionList : userList) {
            sessionList.sendMessage(message);
        }

        /* 전송 후 메시지 DB 저장 */
        InsertChattingDTO chattingMessage = objectMapper.readValue(payload, InsertChattingDTO.class);
        chattingMessage.setChattingInsertTime(LocalDateTime.now());
        chatService.registerChatting(chattingMessage);
    }

    /* 연결이 되었을 때 채팅방 session 추적을 위한 list 에 해당 session 추가 */
    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        userList.add(session);
    }

    /* 연결이 끊겼을 때 채팅방 session 추적을 위한 list 에서 해당 session 삭제 */
    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
        userList.remove(session);
    }
}
