package com.pettory.pettory.counseling.answer.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "counseling_answer_file")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int counselingAnswerFileNum;
    private int counselingAnswerNum;
    private int counselingAnswerFileSize;
    private String counselingAnswerFileDirectory;

    public AnswerFile(int counselingAnswerNum, int counselingAnswerFileSize, String counselingAnswerFileDirectory) {
        this.counselingAnswerNum = counselingAnswerNum;
        this.counselingAnswerFileSize = counselingAnswerFileSize;
        this.counselingAnswerFileDirectory = counselingAnswerFileDirectory;
    }

}