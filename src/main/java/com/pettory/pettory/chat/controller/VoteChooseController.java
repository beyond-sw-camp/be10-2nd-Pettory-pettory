package com.pettory.pettory.chat.controller;

import com.pettory.pettory.chat.dto.votechoose.VoteChooseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votechoose")
public class VoteChooseController {

    /* 1. 투표 선택지 등록 */
    @PostMapping("/votechoice")
    public void insertVoteChoice(@RequestBody VoteChooseDTO voteChooseDTO) {

    }

    /* 2. 투표 선택지 수정 */
    @PutMapping("/votechoice")
    public void modifyVoteChoice(@RequestBody VoteChooseDTO voteChooseDTO) {
        
    }

    /* 3. 투표 선택지 삭제 */
    @DeleteMapping("/votechoice/{voteChoiceUniqueNum}")
    public void deleteVoteChoice(@PathVariable Integer voteChoiceUniqueNum) {

    }

    /* 4. 투표 선택지 조회 */
    @GetMapping("/votechoice/{voteUniqueNum}")
    public void selectVoteChoice(@PathVariable Integer voteUniqueNum) {

    }
}
