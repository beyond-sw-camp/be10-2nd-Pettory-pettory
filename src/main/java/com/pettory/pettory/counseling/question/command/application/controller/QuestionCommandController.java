package com.pettory.pettory.counseling.question.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.counseling.question.command.application.dto.QuestionCommandDTO;
import com.pettory.pettory.counseling.question.command.application.service.QuestionCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@Tag(name = "질문 컨트롤러")
@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionCommandController {

    private final QuestionCommandService questionCommandService;

    public QuestionCommandController(QuestionCommandService questionCommandService) {
        this.questionCommandService = questionCommandService;
    }

    @Operation(summary = "신규 질문 등록", description = "신규 질문을 등록한다.")
    @PostMapping("/questions")
    public ResponseEntity<CommonResponseDTO> createQuestion(
            @RequestPart QuestionCommandDTO questionCommandDTO,
            @RequestPart(required = false) MultipartFile questionCommandImg) throws IOException {

        int counselingQuestionNum = questionCommandService.createQuestion(questionCommandDTO, questionCommandImg);

        return ResponseEntity
                .created(URI.create("/question/questions/" + counselingQuestionNum))
                .build();
    }

    @Operation(summary = "질문번호로 질문 수정", description = "질문번호를 통해 해당하는 질문 정보를 수정한다.")
    @PutMapping("/questions/number/{counselingQuestionNum}")
    public ResponseEntity<CommonResponseDTO> modifyQuestion(@PathVariable int counselingQuestionNum, @RequestPart QuestionCommandDTO questionCommandDTO) {

        questionCommandService.modifyQuestion(questionCommandDTO);

        return ResponseEntity.created(URI.create("/question/questions/number/" + counselingQuestionNum)).build();
    }

    @Operation(summary = "질문제목으로 질문 조회 수 증가", description = "질문제목을 통해 해당하는 질문 조회 수를 증가한다.")
    @PutMapping("/questions/title/{counselingQuestionTitle}")
    public ResponseEntity<CommonResponseDTO> increaseHits(@PathVariable String counselingQuestionTitle, @RequestPart QuestionCommandDTO questionCommandDTO) {

        questionCommandService.increaseHits(questionCommandDTO);

        return ResponseEntity.created(URI.create("/question/questions/title/" + counselingQuestionTitle)).build();
    }

    @Operation(summary = "질문번호로 질문 삭제", description = "질문번호를 통해 해당하는 질문 정보를 삭제한다.")
    @PutMapping("/questions/{counselingQuestionNum}")
    public ResponseEntity<CommonResponseDTO> removeQuestion(@PathVariable int counselingQuestionNum, @RequestPart QuestionCommandDTO questionCommandDTO) {

        questionCommandService.removeQuestion(questionCommandDTO);

        return ResponseEntity.created(URI.create("/question/questions/" + counselingQuestionNum)).build();
    }

}