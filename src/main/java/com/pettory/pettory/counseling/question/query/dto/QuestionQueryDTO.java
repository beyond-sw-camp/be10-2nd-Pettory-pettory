package com.pettory.pettory.counseling.question.query.dto;

import com.pettory.pettory.counseling.question.command.domain.aggregate.QuestionState;
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