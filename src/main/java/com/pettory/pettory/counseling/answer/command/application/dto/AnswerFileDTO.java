package com.pettory.pettory.counseling.answer.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnswerFileDTO {

    private int counselingAnswerFileNum;
    private int counselingAnswerNum;
    private int counselingAnswerFileSize;
    private int counselingAnswerFileDirectory;

}