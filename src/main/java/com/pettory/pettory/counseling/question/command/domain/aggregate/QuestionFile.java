package com.pettory.pettory.counseling.question.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "counseling_question_file")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int counselingQuestionFileNum;
    private int counselingQuestionNum;
    private int counselingQuestionFileSize;
    private String counselingQuestionFileDirectory;

    public QuestionFile(int counselingQuestionNum, int counselingQuestionFileSize, String counselingQuestionFileDirectory) {
        this.counselingQuestionNum = counselingQuestionNum;
        this.counselingQuestionFileSize = counselingQuestionFileSize;
        this.counselingQuestionFileDirectory = counselingQuestionFileDirectory;
    }

}