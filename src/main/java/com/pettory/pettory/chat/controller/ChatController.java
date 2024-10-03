package com.pettory.pettory.chat.controller;

import com.pettory.pettory.chat.dto.chatroom.InsertChatRoomDTO;
import com.pettory.pettory.chat.dto.chatroom.DeleteChatRoomDTO;
import com.pettory.pettory.chat.dto.chatting.ModifyChattingDTO;
import com.pettory.pettory.chat.dto.chatting.SelectChattingDTO;
import com.pettory.pettory.chat.dto.chatting.SoftDeleteChattingDTO;
import com.pettory.pettory.chat.entity.ChatRoom;
import com.pettory.pettory.chat.entity.Chatting;
import com.pettory.pettory.chat.enums.ChatRoomStateEnum;
import com.pettory.pettory.chat.enums.ChattingStateEnum;
import com.pettory.pettory.chat.response.ResponseSelectChattingMessage;
import com.pettory.pettory.chat.service.ChatService;
import com.pettory.pettory.common.CommonResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name="ChatController - 챗 컨트롤러")
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController (ChatService chatService) {
        this.chatService = chatService;
    }

    /* Test 를 위한 Sample testChatting */
    @Operation(summary = "테스트 채팅", description = "testChatting.html 로 이동한다.")
    @GetMapping("/testChatting")
    public String testChattingFeature() {
        return "testChatting";
    }

    /* 1. 채팅방 생성 */
    @Operation(summary = "채팅방 생성", description = "모임이 만들어 질 때 호출되어 채팅방을 만든다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "채팅방 생성 성공")
    })
    @PostMapping("/chatroom")
    public ResponseEntity<CommonResponseDTO> registerChatRoom(@RequestBody InsertChatRoomDTO insertChatRoomDTO) {
        insertChatRoomDTO.setChatroomInsertTime(LocalDateTime.now());

        /* 채팅방 DB 등록*/
        ChatRoom chatroom = chatService.registerChatRoom(insertChatRoomDTO);

        /* 응답헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        /* 응답 데이터 설정 */
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.OK.value(),
                                                        "채팅방 생성 성공", chatroom);

        return ResponseEntity.ok().headers(headers).body(commonResponseDTO);
    }

    /* 2. 채팅방 삭제 */
    @Operation(summary = "채팅방 삭제", description = "상태 값만 DELETE 로 바꾸는 얇은 삭제를 실행한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "채팅방 삭제 성공")
    })
    @DeleteMapping("/chatroom/{chatroomUniqueNum}")
    public ResponseEntity<CommonResponseDTO> deleteChatRoomState(@PathVariable Integer chatroomUniqueNum) {
        DeleteChatRoomDTO deleteChatRoomDTO = new DeleteChatRoomDTO(chatroomUniqueNum);
        deleteChatRoomDTO.setChatroomDeleteTime(LocalDateTime.now());
        deleteChatRoomDTO.setChatroomUpdateTime(LocalDateTime.now());
        deleteChatRoomDTO.setChatroomState(String.valueOf(ChatRoomStateEnum.DELETE));

        /* 응답헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        /* 채팅방 상태 삭제 */
        chatService.deleteChatRoom(deleteChatRoomDTO);

        /* 응답 데이터 설정 */
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.NO_CONTENT.value(),
                                                        "채팅방 삭제 성공", null);

        return ResponseEntity.ok().headers(headers).body(commonResponseDTO);
    }

    /* 채팅방의 채팅 내용 조회 */
    @Operation(summary = "채팅 조회", description = "특정 채팅방의 채팅 내역을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "채팅 조회 성공")
    })
    @GetMapping("/chatroom-chatting/{chatRoomUniqueNum}")
    public ResponseEntity<ResponseSelectChattingMessage> selectChatRoomChatting(@PathVariable Integer chatRoomUniqueNum) {
        /* 응답헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<SelectChattingDTO> chattingList = chatService.selectChatRoomChatting(chatRoomUniqueNum);

        /* 응답 데이터 설정 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("chattingList", chattingList);
        ResponseSelectChattingMessage responseMessage = new ResponseSelectChattingMessage(HttpStatus.OK.value(),
                                                                                  "채팅 조회 성공", responseMap);

        return ResponseEntity.ok().headers(headers).body(responseMessage);
    }

    /* 4. 채팅방의 채팅 내용을 수정 */
    @Operation(summary = "채팅 수정", description = "특정 채팅을 수정한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "채팅 수정 성공")
    })
    @PutMapping("/chatroom-chatting")
    public ResponseEntity<?> modifyChatting(@RequestBody ModifyChattingDTO modifyChattingDTO) {
        /* 응답헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        modifyChattingDTO.setChattingUpdateTime(LocalDateTime.now());
        modifyChattingDTO.setChattingState(String.valueOf(ChattingStateEnum.MODIFY));

        Chatting chatting = chatService.modifyChatting(modifyChattingDTO);

        /* 응답 데이터 설정 */
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.OK.value(),
                                                        "채팅 수정 성공", chatting);

        return ResponseEntity.ok().headers(headers).body(commonResponseDTO);
    }

    /* 채팅방의 채팅 내용을 soft Delete */
    @Operation(summary = "채팅 얇은 삭제", description = "특정 채팅의 상태 값을 DELETE 로 수정하는 얇은 삭제를 한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "채팅방 얇은 삭제 성공")
    })
    @DeleteMapping("/chatroom-chatting-soft/{chattingUniqueNum}")
    public ResponseEntity<CommonResponseDTO> softDeleteChatting(@PathVariable Integer chattingUniqueNum) {
        SoftDeleteChattingDTO softDeleteChattingDTO = new SoftDeleteChattingDTO();
        softDeleteChattingDTO.setChattingUniqueNum(chattingUniqueNum);
        softDeleteChattingDTO.setChattingDeleteTime(LocalDateTime.now());
        softDeleteChattingDTO.setChattingState(String.valueOf(ChattingStateEnum.DELETE));

        chatService.softDeleteChatting(softDeleteChattingDTO);

        /* 응답헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        /* 응답 데이터 설정 */
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.OK.value(),
                                                        "채팅 얇은 삭제 성공", null);

        return ResponseEntity.ok().headers(headers).body(commonResponseDTO);
    }

    /* 채팅방의 채팅 내용을 Hard Delete */
    @Operation(summary = "채팅 깊은 삭제", description = "특정 채팅을 DB 에서 완전히 삭제한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "채팅방 찐 삭제 성공")
    })
    @DeleteMapping("/chatroom-chatting-hard/{chattingUniqueNum}")
    public ResponseEntity<CommonResponseDTO> hardDeleteChatting(@PathVariable Integer chattingUniqueNum) {
        chatService.hardDeleteChatting(chattingUniqueNum);

        /* 응답헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.NO_CONTENT.value(),
                                                        "채팅 찐 삭제 성공", null);

        return ResponseEntity.ok().headers(headers).body(commonResponseDTO);
    }
}
