package com.pettory.pettory.counseling.question.query.controller;

import com.pettory.pettory.counseling.question.query.dto.QuestionQueryDTO;
import com.pettory.pettory.counseling.question.query.service.QuestionQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionQueryController {

    private final QuestionQueryService questionQueryService;

    public QuestionQueryController(QuestionQueryService questionQueryService) {
        this.questionQueryService = questionQueryService;
    }

    @GetMapping("/list")
    public String findQuestionList(Model model) {

        List<QuestionQueryDTO> questionList = questionQueryService.findQuestionList();
        model.addAttribute("questionList", questionList);

        return "question/list";
    }

    @GetMapping("/{counselingQuestionNum}")
    public String findQuestionByNum(@PathVariable int counselingQuestionNum, Model model) {

        QuestionQueryDTO resultQuestion = questionQueryService.findQuestionByNum(counselingQuestionNum);
        model.addAttribute("question", resultQuestion);

        return "question/detail";
    }

    @GetMapping("/searchByCounselingQuestionTitle")
    public String findByCounselingQuestionTitle(@RequestParam String counselingQuestionTitle, Model model) {

        List<QuestionQueryDTO> questionList = questionQueryService.findByCounselingQuestionTitle(counselingQuestionTitle);
        model.addAttribute("questionList", questionList);

        return "question/searchResultByCounselingQuestionTitle";
    }

    @GetMapping("/searchByCounselingQuestionContent")
    public String findByCounselingQuestionContent(@RequestParam String counselingQuestionContent, Model model) {

        List<QuestionQueryDTO> questionList = questionQueryService.findByCounselingQuestionContent(counselingQuestionContent);
        model.addAttribute("questionList", questionList);

        return "question/searchResultByCounselingQuestionContent";
    }

    @GetMapping("/searchByCounselingQuestionTopic")
    public String findByCounselingQuestionTopic(@RequestParam String counselingQuestionTopic, Model model) {

        List<QuestionQueryDTO> questionList = questionQueryService.findByCounselingQuestionTopic(counselingQuestionTopic);
        model.addAttribute("questionList", questionList);

        return "question/searchResultByCounselingQuestionTopic";
    }

    @GetMapping("/searchByUserNickname")
    public String findByUserNickname(@RequestParam String userNickname, Model model) {

        List<QuestionQueryDTO> questionList = questionQueryService.findByUserNickname(userNickname);
        model.addAttribute("questionList", questionList);

        return "question/searchResultByUserNickname";
    }

}