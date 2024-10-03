package com.pettory.pettory.counseling.question.command.application.service;

import com.pettory.pettory.counseling.question.command.application.dto.QuestionCommandDTO;
import com.pettory.pettory.counseling.question.command.domain.aggregate.QuestionState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class QuestionCommandServiceTest {

    @Autowired
    private QuestionCommandService questionCommandService;

    @Mock
    private MultipartFile multipartFile;

    private static Stream<Arguments> getQuestion() {
        return Stream.of(
                Arguments.of(
                        1,
                        1,
                        "신삼 2번출구에서 잃어버린 마루를 찾습니다",
                        "제가 어제 나갔다가 마루를 잃어버렸~~~~~.",
                        1103,
                        "ACTIVE",
                        LocalDateTime.now().toString(),
                        null,
                        null
                )
        );
    }

    private static Stream<Arguments> getQuestion2() {
        return Stream.of(
                Arguments.of(
                        3,
                        "7",
                        "7",
                        7,
                        "ACTIVE",
                        LocalDateTime.now().toString(),
                        null,
                        null
                )
        );
    }

    private static Stream<Arguments> getQuestion3() {
        return Stream.of(
                Arguments.of(
                        1,
                        1,
                        "신삼 3번출구에서 잃어버린 마루를 찾습니다",
                        "제가 그제 나갔다가 마루를 잃어버렸~~~~~.",
                        1103,
                        "ACTIVE",
                        "2024-10-02 11:59:51",
                        null,
                        LocalDateTime.now().toString()
                )
        );
    }

    private static Stream<Arguments> getQuestion4() {
        return Stream.of(
                Arguments.of(
                        1,
                        1,
                        "신삼 2번출구에서 잃어버린 마루를 찾습니다",
                        "제가 어제 나갔다가 마루를 잃어버렸~~~~~.",
                        1103,
                        "DELETE",
                        "2024-10-02 11:59:51",
                        LocalDateTime.now().toString(),
                        null
                )
        );
    }

    @DisplayName("질문 상세 조회 테스트")
    @ParameterizedTest
    @MethodSource("getQuestion")
    void testSelectQuestion(
            int counselingQuestionNum,
            int userId, String counselingQuestionTitle,
            String counselingQuestionContent, int counselingQuestionHits,
            QuestionState counselingQuestionState, String counselingQuestionInsertDatetime,
            String counselingQuestionDeleteDatetime, String counselingQuestionUpdateDatetime) {
        //given
        QuestionCommandDTO question = new QuestionCommandDTO(
                counselingQuestionNum,
                userId,
                counselingQuestionTitle,
                counselingQuestionContent,
                counselingQuestionHits,
                counselingQuestionState,
                counselingQuestionInsertDatetime,
                counselingQuestionDeleteDatetime,
                counselingQuestionUpdateDatetime
        );

        //when
        //then
        Assertions.assertDoesNotThrow(
                () -> questionCommandService.selectQuestion(question)
        );
    }

    @DisplayName("질문 작성 테스트")
    @ParameterizedTest
    @MethodSource("getQuestion2")
    void testRegistQuestionWithFile(
            int userId, String counselingQuestionTitle,
            String counselingQuestionContent, int counselingQuestionHits,
            QuestionState counselingQuestionState, String counselingQuestionInsertDatetime,
            String counselingQuestionDeleteDatetime, String counselingQuestionUpdateDatetime) {
        //given
        QuestionCommandDTO newQuestion = new QuestionCommandDTO(
                userId,
                counselingQuestionTitle,
                counselingQuestionContent,
                counselingQuestionHits,
                counselingQuestionState,
                counselingQuestionInsertDatetime,
                counselingQuestionDeleteDatetime,
                counselingQuestionUpdateDatetime
        );

        //when
        // Mock file upload
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getOriginalFilename()).thenReturn("test.jpg");
        when(multipartFile.getSize()).thenReturn(1024L);

        //then
        Assertions.assertDoesNotThrow(
                () -> questionCommandService.registQuestionWithFile(newQuestion, multipartFile)
        );
    }

    @DisplayName("질문 수정 테스트")
    @ParameterizedTest
    @MethodSource("getQuestion3")
    void testModifyQuestion(
            int counselingQuestionNum,
            int userId, String counselingQuestionTitle,
            String counselingQuestionContent, int counselingQuestionHits,
            QuestionState counselingQuestionState, String counselingQuestionInsertDatetime,
            String counselingQuestionDeleteDatetime, String counselingQuestionUpdateDatetime) {
        //given
        QuestionCommandDTO question = new QuestionCommandDTO(
                counselingQuestionNum,
                userId,
                counselingQuestionTitle,
                counselingQuestionContent,
                counselingQuestionHits,
                counselingQuestionState,
                counselingQuestionInsertDatetime,
                counselingQuestionDeleteDatetime,
                counselingQuestionUpdateDatetime
        );

        //when
        //then
        Assertions.assertDoesNotThrow(
                () -> questionCommandService.modifyQuestion(question)
        );
    }

    @DisplayName("질문 삭제 테스트")
    @ParameterizedTest
    @MethodSource("getQuestion4")
    void testDeleteQuestion(
            int counselingQuestionNum,
            int userId, String counselingQuestionTitle,
            String counselingQuestionContent, int counselingQuestionHits,
            QuestionState counselingQuestionState, String counselingQuestionInsertDatetime,
            String counselingQuestionDeleteDatetime, String counselingQuestionUpdateDatetime) {
        //given
        QuestionCommandDTO question = new QuestionCommandDTO(
                counselingQuestionNum,
                userId,
                counselingQuestionTitle,
                counselingQuestionContent,
                counselingQuestionHits,
                counselingQuestionState,
                counselingQuestionInsertDatetime,
                counselingQuestionDeleteDatetime,
                counselingQuestionUpdateDatetime
        );

        //when
        //then
        Assertions.assertDoesNotThrow(
                () -> questionCommandService.deleteQuestion(question)
        );
    }

}