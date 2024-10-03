package com.pettory.pettory.counseling.question.query.dto;

import com.pettory.pettory.counseling.question.command.domain.aggregate.QuestionState;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "질문Query정보 DTO")
public class QuestionQueryDTO {

    @Schema(description = "질문번호(PK)")
    private int counselingQuestionNum;

    @Schema(description = "회원 번호(FK)")
    private int userId;

    @Schema(description = "질문 제목")
    private String counselingQuestionTitle;

    @Schema(description = "질문 내용")
    private String counselingQuestionContent;

    @Schema(description = "질문 조회수")
    private int counselingQuestionHits;

    @Schema(description = "질문 상태")
    private QuestionState counselingQuestionState;

    @Schema(description = "질문 등록일")
    private String counselingQuestionInsertDatetime;

    @Schema(description = "질문 삭제일")
    private String counselingQuestionDeleteDatetime;

    @Schema(description = "질문 수정일")
    private String counselingQuestionUpdateDatetime;

}