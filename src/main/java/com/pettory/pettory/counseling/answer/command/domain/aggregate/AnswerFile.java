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
    @Column(name = "counseling_answer_file_num")
    private int counselingAnswerFileNum;

    @Column(name = "counseling_answer_num")
    private int counselingAnswerNum;

    @Column(name = "counseling_answer_file_size")
    private int counselingAnswerFileSize;

    @Column(name = "counseling_answer_file_directory")
    private String counselingAnswerFileDirectory;

    public AnswerFile(int counselingAnswerNum, int counselingAnswerFileSize, String counselingAnswerFileDirectory) {
        this.counselingAnswerNum = counselingAnswerNum;
        this.counselingAnswerFileSize = counselingAnswerFileSize;
        this.counselingAnswerFileDirectory = counselingAnswerFileDirectory;
    }

}