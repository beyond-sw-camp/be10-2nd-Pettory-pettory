package com.pettory.mainserver.counseling.answer.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "counseling_answer")
@Getter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counseling_answer_num")
    private int counselingAnswerNum;

    @Column(name = "counseling_question_num")
    private int counselingQuestionNum;

    @Column(name = "counseling_answer_content")
    private String counselingAnswerContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "counseling_answer_state")
    private AnswerState counselingAnswerState  = AnswerState.ACTIVE;

    @Column(name = "counseling_answer_insert_datetime")
    private String counselingAnswerInsertDatetime;

    @Column(name = "counseling_answer_delete_datetime")
    private String counselingAnswerDeleteDatetime;

    @Column(name = "counseling_answer_update_datetime")
    private String counselingAnswerUpdateDatetime;

    @Column(name = "counseling_answer_reanswer_num")
    private int counselingAnswerReanswerNum;

    public void modifyCounselingAnswer(String counselingAnswerContent, String counselingAnswerUpdateDatetime) {
        this.counselingAnswerContent = counselingAnswerContent;
        this.counselingAnswerUpdateDatetime = counselingAnswerUpdateDatetime;
    }

    public void deleteCounselingAnswer(AnswerState counselingAnswerState, String counselingAnswerDeleteDatetime) {
        this.counselingAnswerState = counselingAnswerState;
        this.counselingAnswerDeleteDatetime = counselingAnswerDeleteDatetime;
    }

    public void registSubAnswerWithFile(int counselingAnswerReanswerNum) {
        this.counselingAnswerReanswerNum = counselingAnswerReanswerNum;
    }

}