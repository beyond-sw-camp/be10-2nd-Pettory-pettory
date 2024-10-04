package com.pettory.mainserver.counseling.question.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionFileCommandDTO {

    private int counselingQuestionFileNum;
    private int counselingQuestionNum;
    private int counselingQuestionFileSize;
    private int counselingQuestionFileDirectory;

}