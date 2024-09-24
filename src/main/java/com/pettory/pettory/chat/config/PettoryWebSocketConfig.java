package com.pettory.pettory.chat.config;

import com.pettory.pettory.chat.handler.PettoryChatHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class PettoryWebSocketConfig implements WebSocketConfigurer {

    private final PettoryChatHandler pettoryChatHandler;

    /* Socket Handler 등록 */
    @Override
    public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
        registry.addHandler(pettoryChatHandler, "wss/testChatting").setAllowedOrigins("*");
    }
}
