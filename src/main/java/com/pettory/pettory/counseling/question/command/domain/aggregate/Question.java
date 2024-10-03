package com.pettory.pettory.counseling.question.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "counseling_question")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counseling_question_num")
    private int counselingQuestionNum;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "counseling_question_title")
    private String counselingQuestionTitle;

    @Column(name = "counseling_question_content")
    private String counselingQuestionContent;

    @Column(name = "counseling_question_hits")
    private int counselingQuestionHits;

    @Enumerated(EnumType.STRING)
    @Column(name = "counseling_question_state")
    private QuestionState counselingQuestionState  = QuestionState.ACTIVE;

    @Column(name = "counseling_question_insert_datetime")
    private String counselingQuestionInsertDatetime;

    @Column(name = "counseling_question_delete_datetime")
    private String counselingQuestionDeleteDatetime;

    @Column(name = "counseling_question_update_datetime")
    private String counselingQuestionUpdateDatetime;

    public void modifyCounselingQuestion(String counselingQuestionTitle, String counselingQuestionContent, String counselingQuestionUpdateDatetime) {
        this.counselingQuestionTitle = counselingQuestionTitle;
        this.counselingQuestionContent = counselingQuestionContent;
        this.counselingQuestionUpdateDatetime = counselingQuestionUpdateDatetime;
    }

    public void increaseCounselingQuestionHits(int counselingQuestionHits) {
        this.counselingQuestionHits = counselingQuestionHits;
    }

    public void removeCounselingQuestion(QuestionState counselingQuestionState, String counselingQuestionDeleteDatetime) {
        this.counselingQuestionState = counselingQuestionState;
        this.counselingQuestionDeleteDatetime = counselingQuestionDeleteDatetime;
    }

}