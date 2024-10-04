package com.pettory.pettory.counseling.question.query.service;

import com.pettory.pettory.counseling.question.query.dto.QuestionQueryDTO;
import com.pettory.pettory.counseling.question.query.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionQueryService {

    private final QuestionMapper questionMapper;

    public QuestionQueryService(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    public List<QuestionQueryDTO> findAllQuestions() {

        List<QuestionQueryDTO> questionList = questionMapper.findAllQuestions();

        return questionList;
    }

    public QuestionQueryDTO findQuestionByNum(int counselingQuestionNum) {

        QuestionQueryDTO question = questionMapper.findQuestionByNum(counselingQuestionNum);

        return question;
    }

    public List<QuestionQueryDTO> findQuestionsByTitle(String counselingQuestionTitle) {

        List<QuestionQueryDTO> questionList = questionMapper.findQuestionsByTitle(counselingQuestionTitle);

        return questionList;
    }

    public List<QuestionQueryDTO> findQuestionsByContent(String counselingQuestionContent) {

        List<QuestionQueryDTO> questionList = questionMapper.findQuestionsByContent(counselingQuestionContent);

        return questionList;
    }

    public List<QuestionQueryDTO> findQuestionsByTopic(String counselingQuestionTopic) {

        List<QuestionQueryDTO> questionList = questionMapper.findQuestionsByTopic(counselingQuestionTopic);

        return questionList;
    }

    public List<QuestionQueryDTO> findQuestionsByNickname(String userNickname) {

        List<QuestionQueryDTO> questionList = questionMapper.findQuestionsByNickname(userNickname);

        return questionList;
    }

}