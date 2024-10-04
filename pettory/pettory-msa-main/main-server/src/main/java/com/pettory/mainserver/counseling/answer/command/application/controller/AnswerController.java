package com.pettory.mainserver.counseling.answer.command.application.controller;

import com.pettory.mainserver.counseling.answer.command.application.dto.AnswerDTO;
import com.pettory.mainserver.counseling.answer.command.application.service.AnswerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/regist")
    public void registAnswerWithFile(
            @RequestPart("answer") AnswerDTO answerDTO,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        answerService.registAnswerWithFile(answerDTO, file);
    }

    @PostMapping("/modify")
    public String modifyAnswer(@ModelAttribute AnswerDTO answerDTO) {
        answerService.modifyAnswer(answerDTO);
        return "redirect:/answer/" + answerDTO.getCounselingAnswerNum();
    }

    @DeleteMapping("/delete")
    public String deleteAnswer(@ModelAttribute AnswerDTO answerDTO) {
        answerService.deleteAnswer(answerDTO);
        return "redirect:/answer/list";
    }

    @PostMapping("/reply")
    public void registSubAnswer(
            @RequestPart("answer") AnswerDTO answerDTO,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        answerService.registSubAnswerWithFile(answerDTO, file);
    }

}