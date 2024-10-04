package com.pettory.pettory.counseling.question.query.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionFileQueryDTO {

    private int counselingQuestionFileNum;
    private int counselingQuestionNum;
    private int counselingQuestionFileSize;
    private int counselingQuestionFileDirectory;

}