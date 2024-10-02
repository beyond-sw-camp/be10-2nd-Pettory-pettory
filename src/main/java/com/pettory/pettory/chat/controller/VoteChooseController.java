package com.pettory.pettory.chat.controller;

import com.pettory.pettory.chat.dto.votechoose.VoteChooseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name="VoteChooseController - 투표 선택지 컨트롤러")
@RestController
@RequestMapping("/votechoose")
public class VoteChooseController {

    /* 1. 투표 선택지 등록 */
    @Operation(summary = "투표 선택지 등록", description = "등록한 투표에 대해 선택지를 등록한다." +
                                                         "현재 구현되지 않음, 추후 기능 확장하여 구현할 예정")
    @PostMapping("/votechoice")
    public void insertVoteChoice(@RequestBody VoteChooseDTO voteChooseDTO) {

    }

    /* 2. 투표 선택지 수정 */
    @Operation(summary = "투표 선택지 수정", description = "등록한 투표에 대해 선택지를 수정한다." +
                                                        "현재 구현되지 않음, 추후 기능 확장하여 구현할 예정")
    @PutMapping("/votechoice")
    public void modifyVoteChoice(@RequestBody VoteChooseDTO voteChooseDTO) {
        
    }

    /* 3. 투표 선택지 삭제 */
    @Operation(summary = "투표 선택지 삭제", description = "등록한 투표에 대해 선택지를 삭제한다." +
                                                        "현재 구현되지 않음, 추후 기능 확장하여 구현할 예정")
    @DeleteMapping("/votechoice/{voteChoiceUniqueNum}")
    public void deleteVoteChoice(@PathVariable Integer voteChoiceUniqueNum) {

    }

    /* 4. 투표 선택지 조회 */
    @Operation(summary = "투표 선택지 조회", description = "등록한 투표에 대해 선택지를 조회한다." +
                                                        "현재 구현되지 않음, 추후 기능 확장하여 구현할 예정")
    @GetMapping("/votechoice/{voteUniqueNum}")
    public void selectVoteChoice(@PathVariable Integer voteUniqueNum) {

    }
}
