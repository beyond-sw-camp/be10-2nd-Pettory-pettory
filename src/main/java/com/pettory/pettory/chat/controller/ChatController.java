package com.pettory.pettory.chat.controller;

import com.pettory.pettory.chat.dto.chatroom.InsertChatRoomDTO;
import com.pettory.pettory.chat.dto.chatroom.DeleteChatRoomDTO;
import com.pettory.pettory.chat.dto.chatting.ModifyChattingDTO;
import com.pettory.pettory.chat.dto.chatting.SelectChattingDTO;
import com.pettory.pettory.chat.dto.chatting.SoftDeleteChattingDTO;
import com.pettory.pettory.chat.entity.ChatRoom;
import com.pettory.pettory.chat.enums.ChatRoomStateEnum;
import com.pettory.pettory.chat.response.ResponseSelectChattingMessage;
import com.pettory.pettory.chat.service.ChatService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController (ChatService chatService) {
        this.chatService = chatService;
    }

    /* Test 를 위한 Sample testChatting */
    @GetMapping("/testChatting")
    public String testChattingFeature() {
        return "testChatting";
    }

    /* 1. 채팅방 생성 */
    @PostMapping("/chatroom")
    public ResponseEntity<?> registerChatRoom(@RequestBody InsertChatRoomDTO insertChatRoomDTO) {
        insertChatRoomDTO.setChatroomInsertTime(LocalDateTime.now());

        /* 채팅방 DB 등록*/
        ChatRoom chatroom = chatService.registerChatRoom(insertChatRoomDTO);

        return ResponseEntity.created(
                URI.create("/chat/chatroom/" + chatroom.getChatroomUniqueNum())
        ).build();
    }

    /* 2. 채팅방 삭제 */
    @DeleteMapping("/chatroom/{chatroomUniqueNum}")
    public ResponseEntity<?> deleteChatRoomState(@PathVariable Integer chatroomUniqueNum) {
        DeleteChatRoomDTO deleteChatRoomDTO = new DeleteChatRoomDTO(chatroomUniqueNum);
        deleteChatRoomDTO.setChatroomDeleteTime(LocalDateTime.now());
        deleteChatRoomDTO.setChatroomUpdateTime(LocalDateTime.now());
        deleteChatRoomDTO.setChatroomState(String.valueOf(ChatRoomStateEnum.DELETE));

        /* 채팅방 상태 삭제 */
        chatService.deleteChatRoom(deleteChatRoomDTO);

        return ResponseEntity.created(
                URI.create("/chat/chatroom-state/" + deleteChatRoomDTO.getChatroomUniqueNum())
        ).build();
    }

    /* 채팅방의 채팅 내용 조회 */
    @GetMapping("/chatroom-chatting/{chatRoomUniqueNum}")
    public ResponseEntity<ResponseSelectChattingMessage> selectChatRoomChatting(@PathVariable Integer chatRoomUniqueNum) {
        /* 응답헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<SelectChattingDTO> chattingList = chatService.selectChatRoomChatting(chatRoomUniqueNum);

        /* 응답 데이터 설정 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("chattingList", chattingList);
        ResponseSelectChattingMessage responseMessage = new ResponseSelectChattingMessage(200, "조회 성공", responseMap);

        return ResponseEntity.ok().headers(headers).body(responseMessage);
    }

    /* 4. 채팅방의 채팅 내용을 수정 */
    @PutMapping("/chatroom-chatting/{chattingUniqueNum}")
    public ResponseEntity<?> modifyChatting(@PathVariable Integer chattingUniqueNum,
                                            @RequestBody ModifyChattingDTO modifyChattingDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();

        modifyChattingDTO.setChattingUniqueNum(chattingUniqueNum);
        modifyChattingDTO.setChattingUpdateTime(localDateTime);

        chatService.modifyChatting(modifyChattingDTO);

        return ResponseEntity.created(
                URI.create("/chat/chatting/" + modifyChattingDTO.getChattingUniqueNum())
        ).build();
    }

    /* 채팅방의 채팅 내용을 soft Delete */
    @DeleteMapping("/chatroom-chatting-fake/{chattingUniqueNum}")
    public ResponseEntity<?> softDeleteChatting(@PathVariable Integer chattingUniqueNum,
                                                SoftDeleteChattingDTO softDeleteChattingDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();
        softDeleteChattingDTO.setChattingUniqueNum(chattingUniqueNum);
        softDeleteChattingDTO.setChattingDeleteTime(localDateTime);

        chatService.softDeleteChatting(softDeleteChattingDTO);

        return ResponseEntity.created(
                URI.create("/chat/chatting-soft/" + softDeleteChattingDTO.getChattingUniqueNum())
        ).build();
    }

    /* 채팅방의 채팅 내용을 Hard Delete */
    @DeleteMapping("/chatroom-chatting-real/{chattingUniqueNum}")
    public ResponseEntity<?> hardDeleteChatting(@PathVariable Integer chattingUniqueNum) {
        chatService.hardDeleteChatting(chattingUniqueNum);

        return ResponseEntity.noContent().build();
    }
}
