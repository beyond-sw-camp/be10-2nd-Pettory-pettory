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
    @Column(name = "counseling_question_file_num")
    private int counselingQuestionFileNum;

    @Column(name = "counseling_question_num")
    private int counselingQuestionNum;

    @Column(name = "counseling_question_file_size")
    private int counselingQuestionFileSize;

    @Column(name = "counseling_question_file_directory")
    private String counselingQuestionFileDirectory;

    public QuestionFile(int counselingQuestionNum, int counselingQuestionFileSize, String counselingQuestionFileDirectory) {
        this.counselingQuestionNum = counselingQuestionNum;
        this.counselingQuestionFileSize = counselingQuestionFileSize;
        this.counselingQuestionFileDirectory = counselingQuestionFileDirectory;
    }

}