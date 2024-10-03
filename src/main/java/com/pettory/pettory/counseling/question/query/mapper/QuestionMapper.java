package com.pettory.pettory.counseling.question.query.mapper;

import com.pettory.pettory.counseling.question.query.dto.QuestionQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {

    List<QuestionQueryDTO> findAllQuestions();
    QuestionQueryDTO findQuestionByNum(int counselingQuestionNum);
    List<QuestionQueryDTO> findQuestionsByTitle(String counselingQuestionTitle);
    List<QuestionQueryDTO> findQuestionsByContent(String counselingQuestionContent);
    List<QuestionQueryDTO> findQuestionsByTopic(String counselingQuestionTopic);
    List<QuestionQueryDTO> findQuestionsByNickname(String userNickname);

}