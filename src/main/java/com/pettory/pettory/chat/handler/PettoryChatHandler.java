package com.pettory.pettory.chat.handler;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

/* WebSocket 핸들러 생성
* @Component 를 통한 Bean 등록 */
@Component
public class PettoryChatHandler extends TextWebSocketHandler {

    /* 1. 채팅방에 들어와있는 session 들을 추적할 list 생성 */
    private static final List<WebSocketSession> userList = new ArrayList<>();

    /* 2. 3개의 메소드 오버라이드
    * handleTextMessage
    * afterConnectionEstablished
    * afterConnectionClosed */
    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session,@NonNull TextMessage message) throws Exception {
        /* getPayload : 전송되는 데이터를 뜻함. */
        String sendMessage = message.getPayload();
        System.out.println(sendMessage);

        /* 채팅방에 들어와있는 모든 Session 에게 메시지 전달 */
        for(WebSocketSession sessionList : userList) {
            sessionList.sendMessage(message);
        }
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
