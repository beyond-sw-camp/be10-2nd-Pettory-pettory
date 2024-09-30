package com.pettory.pettory.chat.controller;

import com.pettory.pettory.chat.dto.vote.InsertVoteDTO;
import com.pettory.pettory.chat.dto.vote.ModifyVoteDTO;
import com.pettory.pettory.chat.dto.vote.SelectVoteDTO;
import com.pettory.pettory.chat.dto.vote.SoftDeleteVoteDTO;
import com.pettory.pettory.chat.entity.Vote;
import com.pettory.pettory.chat.enums.VoteStateEnum;
import com.pettory.pettory.chat.response.ResponseChatMessage;
import com.pettory.pettory.chat.service.VoteService;
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
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    /* 투표 조회 */
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
    @PostMapping("/survey")
    public ResponseEntity<Void> insertVote(@RequestBody InsertVoteDTO insertVoteDTO) {
        insertVoteDTO.setVoteInsertTime(LocalDateTime.now());
        insertVoteDTO.setVoteState(String.valueOf(VoteStateEnum.ACTIVE));

        /* DB 등록 */
        Vote vote = voteService.insertVote(insertVoteDTO);

        return ResponseEntity.created(URI.create("/vote/survey/" + vote.getVoteUniqueNum())).build();
    }

    /* 2. 투표 수정 */
    @PutMapping("/survey/{voteUniqueNum}")
    public ResponseEntity<ResponseChatMessage> modifyVote(@RequestBody ModifyVoteDTO modifyVoteDTO) {
        /* 응답헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        /* 데이터 셋팅 */
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
    @DeleteMapping("/survey-soft/{voteUniqueNum}")
    public ResponseEntity<Void> softDeleteVote(@RequestBody SoftDeleteVoteDTO softDeleteVoteDTO) {
        softDeleteVoteDTO.setVoteDeleteTime(LocalDateTime.now());
        voteService.softDeleteVote(softDeleteVoteDTO);

        return ResponseEntity.created(
                URI.create("/survey-soft/" + softDeleteVoteDTO.getVoteUniqueNum())
        ).build();
    }

    /* 4. 리얼 삭제 */
    @DeleteMapping("/survey-hard/{voteUniqueNum}")
    public ResponseEntity<Void> hardDeleteVote(@PathVariable Integer voteUniqueNum) {
        voteService.hardDeleteVote(voteUniqueNum);

        return ResponseEntity.noContent().build();
    }

}
