package com.pettory.mainserver.counseling.question.command.domain.aggregate;

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
    private int counselingQuestionNum;
    private int userId;
    private String counselingQuestionTitle;
    private String counselingQuestionContent;
    private int counselingQuestionHits;

    @Enumerated(EnumType.STRING)
    private QuestionState counselingQuestionState  = QuestionState.ACTIVE;

    private String counselingQuestionInsertDatetime;
    private String counselingQuestionDeleteDatetime;
    private String counselingQuestionUpdateDatetime;

    public void increaseCounselingQuestionHits(int counselingQuestionHits) {
        this.counselingQuestionHits = counselingQuestionHits;
    }

    public void modifyCounselingQuestion(String counselingQuestionTitle, String counselingQuestionContent, String counselingQuestionUpdateDatetime) {
        this.counselingQuestionTitle = counselingQuestionTitle;
        this.counselingQuestionContent = counselingQuestionContent;
        this.counselingQuestionUpdateDatetime = counselingQuestionUpdateDatetime;
    }

    public void deleteCounselingQuestion(QuestionState counselingQuestionState, String counselingQuestionDeleteDatetime) {
        this.counselingQuestionState = counselingQuestionState;
        this.counselingQuestionDeleteDatetime = counselingQuestionDeleteDatetime;
    }

}