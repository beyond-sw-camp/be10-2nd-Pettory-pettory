package com.pettory.pettory.counseling.question.query.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "질문파일Query정보 DTO")
public class QuestionFileQueryDTO {

    @Schema(description = "질문파일번호(PK)")
    private int counselingQuestionFileNum;

    @Schema(description = "질문 번호(FK)")
    private int counselingQuestionNum;

    @Schema(description = "질문 파일크기")
    private int counselingQuestionFileSize;

    @Schema(description = "질문 파일경로")
    private int counselingQuestionFileDirectory;

}