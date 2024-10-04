package com.pettory.mainserver.chat.controller;

import com.pettory.mainserver.chat.dto.vote.InsertVoteDTO;
import com.pettory.mainserver.chat.dto.vote.ModifyVoteDTO;
import com.pettory.mainserver.chat.dto.vote.SelectVoteDTO;
import com.pettory.mainserver.chat.dto.vote.SoftDeleteVoteDTO;
import com.pettory.mainserver.chat.entity.Vote;
import com.pettory.mainserver.chat.enums.VoteStateEnum;
import com.pettory.mainserver.chat.response.ResponseChatMessage;
import com.pettory.mainserver.chat.service.VoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name="VoteController - 투표 컨트롤러")
@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    /* 투표 조회 */
    @Operation(summary = "투표 조회", description = "현 채팅방의 투표를 조회할 수 있다. " +
            "                                      현재는 사용하지 않음, 추후 기능 확장하여 사용할 예정")
    @GetMapping("/chatroom-survey/{voteChatroomUniqueNum}")
    public ResponseEntity<ResponseChatMessage> selectVoteList(@PathVariable Integer voteChatroomUniqueNum) {
        /* 응답헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<SelectVoteDTO> voteList = voteService.selectVoteList(voteChatroomUniqueNum);

        /* 응답 데이터 설정 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("voteList", voteList);
        ResponseChatMessage responseMessage = new ResponseChatMessage(200, "조회 성공", responseMap);

        return ResponseEntity.ok().headers(headers).body(responseMessage);
    }

    /* 1. 투표 삽입 */
    @Operation(summary = "투표 등록", description = "현 채팅방에 투표를 등록할 수 있다. " +
            "                                      현재는 사용하지 않음, 추후 기능 확장하여 사용할 예정")
    @PostMapping("/survey")
    public ResponseEntity<Void> insertVote(@RequestBody InsertVoteDTO insertVoteDTO) {
        insertVoteDTO.setVoteInsertTime(LocalDateTime.now());
        insertVoteDTO.setVoteState(String.valueOf(VoteStateEnum.ACTIVE));

        /* DB 등록 */
        Vote vote = voteService.insertVote(insertVoteDTO);

        return ResponseEntity.created(URI.create("/vote/survey/" + vote.getVoteUniqueNum())).build();
    }

    /* 2. 투표 수정 */
    @Operation(summary = "투표 수정", description = "현 채팅방의 투표를 수정할 수 있다. " +
            "                                      현재는 사용하지 않음, 추후 기능 확장하여 사용할 예정")
    @PutMapping("/survey/{voteUniqueNum}")
    public ResponseEntity<ResponseChatMessage> modifyVote(@PathVariable Integer voteUniqueNum,
                                                          @RequestBody ModifyVoteDTO modifyVoteDTO) {
        /* 응답헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        /* 데이터 셋팅 */
        modifyVoteDTO.setVoteUniqueNum(voteUniqueNum);
        modifyVoteDTO.setVoteUpdateTime(LocalDateTime.now());
        modifyVoteDTO.setVoteState(String.valueOf(VoteStateEnum.MODIFY));

        /* DB 수정 */
        Vote vote = voteService.modifyVote(modifyVoteDTO);

        /* 응답 데이터 설정 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("vote", vote);
        ResponseChatMessage responseMessage = new ResponseChatMessage(200, "수정 성공", responseMap);

        return ResponseEntity.ok().headers(headers).body(responseMessage);
    }

    /* 3. 투표 얇은 삭제 */
    @Operation(summary = "투표 얇은 삭제", description = "투표 상태를 DELETE 로 변경하여 얇은 삭제를 진행한다." +
            "                                          현재는 사용하지 않음, 추후 기능 확장하여 사용할 예정")
    @DeleteMapping("/survey-soft/{voteUniqueNum}")
    public ResponseEntity<Void> softDeleteVote(@PathVariable Integer voteUniqueNum) {
        SoftDeleteVoteDTO softDeleteVoteDTO = new SoftDeleteVoteDTO();
        softDeleteVoteDTO.setVoteUniqueNum(voteUniqueNum);
        softDeleteVoteDTO.setVoteDeleteTime(LocalDateTime.now());
        softDeleteVoteDTO.setVoteState(String.valueOf(VoteStateEnum.DELETE));
        voteService.softDeleteVote(softDeleteVoteDTO);

        return ResponseEntity.created(
                URI.create("/survey-soft/" + softDeleteVoteDTO.getVoteUniqueNum())
        ).build();
    }

    /* 4. 리얼 삭제 */
    @Operation(summary = "투표 완전 삭제", description = "투표를 DB 에서 완전 삭제한다." +
            "                                           현재는 사용하지 않음, 추후 기능 확장하여 사용할 예정")
    @DeleteMapping("/survey-hard/{voteUniqueNum}")
    public ResponseEntity<Void> hardDeleteVote(@PathVariable Integer voteUniqueNum) {
        voteService.hardDeleteVote(voteUniqueNum);

        return ResponseEntity.noContent().build();
    }

}
