package com.pettory.mainserver.counseling.question.query.mapper;

import com.pettory.mainserver.counseling.question.query.dto.QuestionQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {

    List<QuestionQueryDTO> findQuestionList();
    QuestionQueryDTO findQuestionByNum(int counselingQuestionNum);
    List<QuestionQueryDTO> findByCounselingQuestionTitle(String counselingQuestionTitle);
    List<QuestionQueryDTO> findByCounselingQuestionContent(String counselingQuestionContent);
    List<QuestionQueryDTO> findByCounselingQuestionTopic(String counselingQuestionTopic);
    List<QuestionQueryDTO> findByUserNickname(String userNickname);

}