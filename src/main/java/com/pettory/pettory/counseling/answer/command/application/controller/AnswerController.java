package com.pettory.pettory.counseling.answer.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.counseling.answer.command.application.dto.AnswerDTO;
import com.pettory.pettory.counseling.answer.command.application.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@Tag(name = "답변 컨트롤러")
@RestController
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Operation(summary = "신규 답변 등록", description = "신규 답변을 등록한다.")
    @PostMapping("/answers")
    public ResponseEntity<CommonResponseDTO> createAnswer(
            @RequestPart AnswerDTO answerDTO,
            @RequestPart(required = false) MultipartFile answerImg) throws IOException {

        int counselingAnswerNum = answerService.createAnswer(answerDTO, answerImg);

        return ResponseEntity
                .created(URI.create("/answer/answers/" + counselingAnswerNum))
                .build();
    }

    @Operation(summary = "답변번호로 답변 수정", description = "답변번호를 통해 해당하는 답변 정보를 수정한다.")
    @PutMapping("/answers/number/{counselingAnswerNum}")
    public ResponseEntity<CommonResponseDTO> modifyAnswer(@PathVariable int counselingAnswerNum, @RequestPart AnswerDTO answerDTO) {

        answerService.modifyAnswer(answerDTO);

        return ResponseEntity.created(URI.create("/answer/answers/number/" + counselingAnswerNum)).build();
    }

    @Operation(summary = "답변번호로 답변 삭제", description = "답변번호를 통해 해당하는 답변 정보를 삭제한다.")
    @PutMapping("/answers/{counselingAnswerNum}")
    public ResponseEntity<CommonResponseDTO> removeAnswer(@PathVariable int counselingAnswerNum, @RequestPart AnswerDTO answerDTO) {

        answerService.removeAnswer(answerDTO);

        return ResponseEntity.created(URI.create("/answer/answers/" + counselingAnswerNum)).build();
    }

    @Operation(summary = "신규 재답변 등록", description = "신규 재답변을 등록한다.")
    @PostMapping("/subanswers")
    public ResponseEntity<CommonResponseDTO> createSubAnswer(
            @RequestPart AnswerDTO answerDTO,
            @RequestPart(required = false) MultipartFile subAnswerImg) throws IOException {

        int counselingAnswerReanswerNum = answerService.createSubAnswer(answerDTO, subAnswerImg);

        return ResponseEntity
                .created(URI.create("/answer/subanswers/" + counselingAnswerReanswerNum))
                .build();
    }

}