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

    public List<QuestionQueryDTO> findQuestionList() {

        List<QuestionQueryDTO> questionList = questionMapper.findQuestionList();

        return questionList;
    }

    public QuestionQueryDTO findQuestionByNum(int counselingQuestionNum) {

        QuestionQueryDTO question = questionMapper.findQuestionByNum(counselingQuestionNum);

        return question;
    }

    public List<QuestionQueryDTO> findByCounselingQuestionTitle(String counselingQuestionTitle) {

        List<QuestionQueryDTO> questionList = questionMapper.findByCounselingQuestionTitle(counselingQuestionTitle);

        return questionList;
    }

    public List<QuestionQueryDTO> findByCounselingQuestionContent(String counselingQuestionContent) {

        List<QuestionQueryDTO> questionList = questionMapper.findByCounselingQuestionContent(counselingQuestionContent);

        return questionList;
    }

    public List<QuestionQueryDTO> findByCounselingQuestionTopic(String counselingQuestionTopic) {

        List<QuestionQueryDTO> questionList = questionMapper.findByCounselingQuestionTopic(counselingQuestionTopic);

        return questionList;
    }

    public List<QuestionQueryDTO> findByUserNickname(String userNickname) {

        List<QuestionQueryDTO> questionList = questionMapper.findByUserNickname(userNickname);

        return questionList;
    }

}