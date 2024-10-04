package com.pettory.mainserver.counseling.question.command.application.dto;

import com.pettory.mainserver.counseling.question.command.domain.aggregate.QuestionState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionCommandDTO {

    private int counselingQuestionNum;
    private int userId;
    private String counselingQuestionTitle;
    private String counselingQuestionContent;
    private int counselingQuestionHits;
    private QuestionState counselingQuestionState;
    private String counselingQuestionInsertDatetime;
    private String counselingQuestionDeleteDatetime;
    private String counselingQuestionUpdateDatetime;

    public QuestionCommandDTO() {
    }

    public QuestionCommandDTO(int counselingQuestionNum, int userId, String counselingQuestionTitle, String counselingQuestionContent, int counselingQuestionHits, QuestionState counselingQuestionState, String counselingQuestionInsertDatetime, String counselingQuestionDeleteDatetime, String counselingQuestionUpdateDatetime) {
        this.counselingQuestionNum = counselingQuestionNum;
        this.userId = userId;
        this.counselingQuestionTitle = counselingQuestionTitle;
        this.counselingQuestionContent = counselingQuestionContent;
        this.counselingQuestionHits = counselingQuestionHits;
        this.counselingQuestionState = counselingQuestionState;
        this.counselingQuestionInsertDatetime = counselingQuestionInsertDatetime;
        this.counselingQuestionDeleteDatetime = counselingQuestionDeleteDatetime;
        this.counselingQuestionUpdateDatetime = counselingQuestionUpdateDatetime;
    }

    public QuestionCommandDTO(int userId, String counselingQuestionTitle, String counselingQuestionContent, int counselingQuestionHits, QuestionState counselingQuestionState, String counselingQuestionInsertDatetime, String counselingQuestionDeleteDatetime, String counselingQuestionUpdateDatetime) {
        this.userId = userId;
        this.counselingQuestionTitle = counselingQuestionTitle;
        this.counselingQuestionContent = counselingQuestionContent;
        this.counselingQuestionHits = counselingQuestionHits;
        this.counselingQuestionState = counselingQuestionState;
        this.counselingQuestionInsertDatetime = counselingQuestionInsertDatetime;
        this.counselingQuestionDeleteDatetime = counselingQuestionDeleteDatetime;
        this.counselingQuestionUpdateDatetime = counselingQuestionUpdateDatetime;
    }

}