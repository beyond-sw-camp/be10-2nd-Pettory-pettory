package com.pettory.pettory.counseling.question.query.service;

import com.pettory.pettory.counseling.question.query.dto.QuestionQueryDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class QuestionQueryServiceTest {

    @Autowired
    private QuestionQueryService questionQueryService;

    @Test
    @DisplayName("질문 목록 조회 테스트")
    void testFindQuestionList() {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<QuestionQueryDTO> questions = questionQueryService.findQuestionList();
                    questions.forEach(System.out::println);
                }
        );
    }

    @DisplayName("질문 번호별 질문 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2"})
    void testFindQuestionByNum(int counselingQuestionNum) {
        Assertions.assertDoesNotThrow(
                () -> {
                    QuestionQueryDTO question = questionQueryService.findQuestionByNum(counselingQuestionNum);
                    System.out.println(question);
                }
        );
    }

    @DisplayName("질문 제목별 질문 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"신삼", "할거없으면"})
    void testFindByCounselingQuestionTitle(String counselingQuestionTitle) {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<QuestionQueryDTO> questions = questionQueryService.findByCounselingQuestionTitle(counselingQuestionTitle);
                    questions.forEach(System.out::println);
                }
        );
    }

    @DisplayName("질문 내용별 질문 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"제가", "호박나이트에서"})
    void testFindByCounselingQuestionContent(String counselingQuestionContent) {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<QuestionQueryDTO> questions = questionQueryService.findByCounselingQuestionContent(counselingQuestionContent);
                    questions.forEach(System.out::println);
                }
        );
    }

    @DisplayName("질문 제목+내용별 질문 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"마루"})
    void testFindByCounselingQuestionTopic(String counselingQuestionTopic) {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<QuestionQueryDTO> questions = questionQueryService.findByCounselingQuestionTopic(counselingQuestionTopic);
                    questions.forEach(System.out::println);
                }
        );
    }

    @DisplayName("회원 닉네임별 질문 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"민권쓰", "찌유"})
    void testFindByUserNickname(String userNickname) {
        Assertions.assertDoesNotThrow(
                () -> {
                    List<QuestionQueryDTO> questions = questionQueryService.findByUserNickname(userNickname);
                    questions.forEach(System.out::println);
                }
        );
    }

}