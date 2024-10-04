package com.pettory.mainserver.counseling.question.query.dto;

import com.pettory.mainserver.counseling.question.command.domain.aggregate.QuestionState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionQueryDTO {

    private int counselingQuestionNum;
    private int userId;
    private String counselingQuestionTitle;
    private String counselingQuestionContent;
    private int counselingQuestionHits;
    private QuestionState counselingQuestionState;
    private String counselingQuestionInsertDatetime;
    private String counselingQuestionDeleteDatetime;
    private String counselingQuestionUpdateDatetime;

}