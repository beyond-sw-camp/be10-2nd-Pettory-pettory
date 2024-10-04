package com.pettory.pettory.counseling.question.query.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.counseling.question.query.dto.QuestionQueryDTO;
import com.pettory.pettory.counseling.question.query.service.QuestionQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "질문 컨트롤러")
@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionQueryController {

    private final QuestionQueryService questionQueryService;

    public QuestionQueryController(QuestionQueryService questionQueryService) {
        this.questionQueryService = questionQueryService;
    }

    @Operation(
            summary = "전체 질문 조회", description = "전체 질문 목록을 조회한다."
    )
    @GetMapping("/questions")
    public ResponseEntity<CommonResponseDTO> findAllQuestions() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<QuestionQueryDTO> questions = questionQueryService.findAllQuestions();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("questions", questions);
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(200, "조회 성공", responseMap);

        return ResponseEntity.ok().headers(headers).body(commonResponseDTO);
    }

    @Operation(
            summary = "질문번호로 질문 조회",
            description = "질문번호를 통해 해당하는 질문 정보를 조회한다."
    )
    @GetMapping("/questions/number/{counselingQuestionNum}")
    public ResponseEntity<CommonResponseDTO> findQuestionByNum(@PathVariable int counselingQuestionNum) {
        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        QuestionQueryDTO foundQuestion = questionQueryService.findQuestionByNum(counselingQuestionNum);
        /* 응답 데이터 설정 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("question", foundQuestion);
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(200, "조회 성공", responseMap);

        return ResponseEntity.ok().headers(headers).body(commonResponseDTO);
    }

    @Operation(
            summary = "질문제목으로 질문 조회",
            description = "질문제목을 통해 해당하는 질문 정보를 조회한다."
    )
    @GetMapping("/questions/title/{counselingQuestionTitle}")
    public ResponseEntity<CommonResponseDTO> findQuestionsByTitle(@PathVariable String counselingQuestionTitle) {
        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<QuestionQueryDTO> foundQuestions = questionQueryService.findQuestionsByTitle(counselingQuestionTitle);
        /* 응답 데이터 설정 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("question", foundQuestions);
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(200, "조회 성공", responseMap);

        return ResponseEntity.ok().headers(headers).body(commonResponseDTO);
    }

    @Operation(
            summary = "질문내용으로 질문 조회",
            description = "질문내용을 통해 해당하는 질문 정보를 조회한다."
    )
    @GetMapping("/questions/content/{counselingQuestionContent}")
    public ResponseEntity<CommonResponseDTO> findQuestionsByContent(@PathVariable String counselingQuestionContent) {
        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<QuestionQueryDTO> foundQuestions = questionQueryService.findQuestionsByContent(counselingQuestionContent);
        /* 응답 데이터 설정 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("question", foundQuestions);
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(200, "조회 성공", responseMap);

        return ResponseEntity.ok().headers(headers).body(commonResponseDTO);
    }

    @Operation(
            summary = "질문제목과내용으로 질문 조회",
            description = "질문제목과내용을 통해 해당하는 질문 정보를 조회한다."
    )
    @GetMapping("/questions/topic/{counselingQuestionTopic}")
    public ResponseEntity<CommonResponseDTO> findQuestionsByTopic(@PathVariable String counselingQuestionTopic) {
        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<QuestionQueryDTO> foundQuestions = questionQueryService.findQuestionsByTopic(counselingQuestionTopic);
        /* 응답 데이터 설정 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("question", foundQuestions);
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(200, "조회 성공", responseMap);

        return ResponseEntity.ok().headers(headers).body(commonResponseDTO);
    }

    @Operation(
            summary = "회원닉네임으로 질문 조회",
            description = "회원닉네임을 통해 해당하는 질문 정보를 조회한다."
    )
    @GetMapping("/questions/nickname/{userNickname}")
    public ResponseEntity<CommonResponseDTO> findQuestionsByNickname(@PathVariable String userNickname) {
        /* 응답 헤더 설정 */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        List<QuestionQueryDTO> foundQuestions = questionQueryService.findQuestionsByNickname(userNickname);
        /* 응답 데이터 설정 */
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("question", foundQuestions);
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(200, "조회 성공", responseMap);

        return ResponseEntity.ok().headers(headers).body(commonResponseDTO);
    }

}