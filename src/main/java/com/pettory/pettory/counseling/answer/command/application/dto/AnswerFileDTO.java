package com.pettory.pettory.counseling.answer.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "답변파일정보 DTO")
public class AnswerFileDTO {

    @Schema(description = "답변파일번호(PK)")
    private int counselingAnswerFileNum;

    @Schema(description = "답변 번호(FK)")
    private int counselingAnswerNum;

    @Schema(description = "답변 파일크기")
    private int counselingAnswerFileSize;

    @Schema(description = "답변 파일경로")
    private int counselingAnswerFileDirectory;

}