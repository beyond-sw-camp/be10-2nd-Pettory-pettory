package com.pettory.mainserver.counseling.question.command.application.controller;

import com.pettory.mainserver.counseling.question.command.application.dto.QuestionCommandDTO;
import com.pettory.mainserver.counseling.question.command.application.service.QuestionCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionCommandController {

    private final QuestionCommandService questionCommandService;

    public QuestionCommandController(QuestionCommandService questionCommandService) {
        this.questionCommandService = questionCommandService;
    }

    @PostMapping("/select")
    public String selectQuestion(@ModelAttribute QuestionCommandDTO questionCommandDTO) {
        questionCommandService.selectQuestion(questionCommandDTO);
        return "redirect:/question/" + questionCommandDTO.getCounselingQuestionNum();
    }

    @PostMapping("/regist")
    public void registQuestionWithFile(
            @RequestPart("question") QuestionCommandDTO questionCommandDTO,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        questionCommandService.registQuestionWithFile(questionCommandDTO, file);
    }

    @PostMapping("/modify")
    public String modifyQuestion(@ModelAttribute QuestionCommandDTO questionCommandDTO) {
        questionCommandService.modifyQuestion(questionCommandDTO);
        return "redirect:/question/" + questionCommandDTO.getCounselingQuestionNum();
    }

    @DeleteMapping("/delete")
    public String deleteQuestion(@ModelAttribute QuestionCommandDTO questionCommandDTO) {
        questionCommandService.deleteQuestion(questionCommandDTO);
        return "redirect:/question/list";
    }

}