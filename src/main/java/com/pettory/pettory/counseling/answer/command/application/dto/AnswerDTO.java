package com.pettory.pettory.counseling.answer.command.application.dto;

import com.pettory.pettory.counseling.answer.command.domain.aggregate.AnswerState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnswerDTO {

    private int counselingAnswerNum;
    private int counselingQuestionNum;
    private String counselingAnswerContent;
    private AnswerState counselingAnswerState;
    private String counselingAnswerInsertDatetime;
    private String counselingAnswerDeleteDatetime;
    private String counselingAnswerUpdateDatetime;
    private int counselingAnswerReanswerNum;

    public AnswerDTO() {
    }

    public AnswerDTO(int counselingAnswerNum, int counselingQuestionNum, String counselingAnswerContent, AnswerState counselingAnswerState, String counselingAnswerInsertDatetime, String counselingAnswerDeleteDatetime, String counselingAnswerUpdateDatetime, int counselingAnswerReanswerNum) {
        this.counselingAnswerNum = counselingAnswerNum;
        this.counselingQuestionNum = counselingQuestionNum;
        this.counselingAnswerContent = counselingAnswerContent;
        this.counselingAnswerState = counselingAnswerState;
        this.counselingAnswerInsertDatetime = counselingAnswerInsertDatetime;
        this.counselingAnswerDeleteDatetime = counselingAnswerDeleteDatetime;
        this.counselingAnswerUpdateDatetime = counselingAnswerUpdateDatetime;
        this.counselingAnswerReanswerNum = counselingAnswerReanswerNum;
    }

    public AnswerDTO(int counselingQuestionNum, String counselingAnswerContent, AnswerState counselingAnswerState, String counselingAnswerInsertDatetime, String counselingAnswerDeleteDatetime, String counselingAnswerUpdateDatetime, int counselingAnswerReanswerNum) {
        this.counselingQuestionNum = counselingQuestionNum;
        this.counselingAnswerContent = counselingAnswerContent;
        this.counselingAnswerState = counselingAnswerState;
        this.counselingAnswerInsertDatetime = counselingAnswerInsertDatetime;
        this.counselingAnswerDeleteDatetime = counselingAnswerDeleteDatetime;
        this.counselingAnswerUpdateDatetime = counselingAnswerUpdateDatetime;
        this.counselingAnswerReanswerNum = counselingAnswerReanswerNum;
    }

}