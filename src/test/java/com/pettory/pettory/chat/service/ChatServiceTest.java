package com.pettory.pettory.chat.service;

import com.pettory.pettory.chat.dto.ChatRoomDTO;
import com.pettory.pettory.chat.dto.chatting.InsertChattingDTO;
import com.pettory.pettory.chat.dto.chatting.ModifyChattingDTO;
import com.pettory.pettory.chat.dto.chatting.SoftDeleteChattingDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@SpringBootTest
class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    private static Stream<Arguments> getChatRoom() {
        return Stream.of(
                Arguments.of(
                        6,
                        null,
                        null,
                        null,
                        "ACTIVE",
                        "WALKING",
                        5
                )
        );
    }

    private static Stream<Arguments> getModifyChatRoom() {
        return Stream.of(
                Arguments.of(
                        6,
                        null,
                        null,
                        null,
                        "DELETE",
                        "WALKING",
                        5
                )
        );
    }

    private static Stream<Arguments> getRegisterChatting() {
        return Stream.of(
                Arguments.of(
                        3,
                        3,
                        "안녕하세요",
                        null,
                        "ACTIVE",
                        1
                )
        );
    }

    private static Stream<Arguments> getModifyChatting() {
        return Stream.of(
                Arguments.of(
                        2,
                        3,
                        "안녕하세요 수정합니다!",
                        null,
                        "MODIFY",
                        1
                )
        );
    }

    private static Stream<Arguments> getSoftDeleteChatting() {
        return Stream.of(
                Arguments.of(
                        3,
                        3,
                        null,
                        "DELETE"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getChatRoom")
    void testRegisterChatRoom(int chatRoomUniqueNum, LocalDateTime insertTime, LocalDateTime updateTime,
                              LocalDateTime deleteTime, String chatRoomState, String chatRoomType,
                              int chatRoomTypeNum) {
        insertTime = LocalDateTime.now();
        updateTime = null;
        deleteTime = null;
        ChatRoomDTO newChatRoom = new ChatRoomDTO(
                chatRoomUniqueNum,
                insertTime,
                updateTime,
                deleteTime,
                chatRoomState,
                chatRoomType,
                chatRoomTypeNum
        );

        Assertions.assertDoesNotThrow(
                () -> chatService.registerChatRoom(newChatRoom)
        );
    }

    @ParameterizedTest
    @MethodSource("getModifyChatRoom")
    void modifyChatRoom(int chatRoomUniqueNum, LocalDateTime insertTime, LocalDateTime updateTime,
                        LocalDateTime deleteTime, String chatRoomState, String chatRoomType,
                        int chatRoomTypeNum) {
        updateTime = LocalDateTime.now();
        deleteTime = LocalDateTime.now();
        ChatRoomDTO modifyChatRoom = new ChatRoomDTO(
                chatRoomUniqueNum,
                insertTime,
                updateTime,
                deleteTime,
                chatRoomState,
                chatRoomType,
                chatRoomTypeNum
        );

        Assertions.assertDoesNotThrow(
                () -> chatService.modifyChatRoom(modifyChatRoom)
        );
    }

    @ParameterizedTest
    @MethodSource("getRegisterChatting")
    void registerChatting(int chattingUniqueNum, int chattingChatRoomUnique,
                          String chattingContent, LocalDateTime insertTime,
                          String chattingState, int userId) {
        insertTime = LocalDateTime.now();

        InsertChattingDTO newChatting = new InsertChattingDTO(
                chattingUniqueNum,
                chattingChatRoomUnique,
                chattingContent,
                insertTime,
                chattingState,
                userId
        );

        Assertions.assertDoesNotThrow(
                () -> chatService.registerChatting(newChatting)
        );
    }

    @ParameterizedTest
    @MethodSource("getModifyChatting")
    void modifyChatting(int chattingUniqueNum, int chattingChatRoomUnique,
                        String chattingContent, LocalDateTime updateTime,
                        String chattingState) {
        updateTime = LocalDateTime.now();

        ModifyChattingDTO modifyChatting = new ModifyChattingDTO(
                chattingUniqueNum,
                chattingChatRoomUnique,
                chattingContent,
                updateTime,
                chattingState
        );

        Assertions.assertDoesNotThrow(
                () -> chatService.modifyChatting(modifyChatting)
        );
    }

    @ParameterizedTest
    @MethodSource("getSoftDeleteChatting")
    void softDeleteChatting(int chattingUniqueNum, int chattingChatRoomUniqueNum,
                            LocalDateTime chattingDeleteTIme, String chattingState) {
        chattingDeleteTIme = LocalDateTime.now();

        SoftDeleteChattingDTO softDeleteChattingDTO = new SoftDeleteChattingDTO(
                chattingUniqueNum,
                chattingChatRoomUniqueNum,
                chattingDeleteTIme,
                chattingState
        );

        Assertions.assertDoesNotThrow(
                () -> chatService.softDeleteChatting(softDeleteChattingDTO)
        );
    }

    @Test
    void hardDeleteChatting() {
        Assertions.assertDoesNotThrow(
                () -> chatService.hardDeleteChatting(3)
        );
    }
}