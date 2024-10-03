package com.pettory.pettory.counseling.answer.command.application.dto;

import com.pettory.pettory.counseling.answer.command.domain.aggregate.AnswerState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "답변정보 DTO")
public class AnswerDTO {

    @Schema(description = "답변번호(PK)")
    private int counselingAnswerNum;

    @Schema(description = "질문 번호(FK)")
    private int counselingQuestionNum;

    @Schema(description = "답변 내용")
    private String counselingAnswerContent;

    @Schema(description = "답변 상태")
    private AnswerState counselingAnswerState;

    @Schema(description = "답변 등록일")
    private String counselingAnswerInsertDatetime;

    @Schema(description = "답변 삭제일")
    private String counselingAnswerDeleteDatetime;

    @Schema(description = "답변 수정일")
    private String counselingAnswerUpdateDatetime;

    @Schema(description = "재답변 번호")
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