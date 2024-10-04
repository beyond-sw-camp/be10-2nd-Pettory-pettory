package com.pettory.pettory.counseling.answer.command.application.service;

import com.pettory.pettory.counseling.answer.command.application.dto.AnswerDTO;
import com.pettory.pettory.counseling.answer.command.domain.aggregate.AnswerState;
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

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {

    @Autowired
    private AnswerService answerService;

    @Mock
    private MultipartFile multipartFile;

    private static Stream<Arguments> getAnswer() {
        return Stream.of(
                Arguments.of(
                        6,
                        "7",
                        "ACTIVE",
                        LocalDateTime.now().toString(),
                        null,
                        null,
                        -1
                )
        );
    }

    private static Stream<Arguments> getAnswer2() {
        return Stream.of(
                Arguments.of(
                        19,
                        6,
                        "제가 40분전에 3번출구 쪽에서 본거같아요",
                        "ACTIVE",
                        "2023-01-01 10:00:00",
                        null,
                        LocalDateTime.now().toString(),
                        -1
                )
        );
    }

    private static Stream<Arguments> getAnswer3() {
        return Stream.of(
                Arguments.of(
                        19,
                        6,
                        "제가 30분전에 3번출구 쪽에서 본거같아요",
                        "DELETE",
                        "2023-01-01 10:00:00",
                        LocalDateTime.now().toString(),
                        null,
                        -1
                )
        );
    }

    private static Stream<Arguments> getSubAnswer() {
        return Stream.of(
                Arguments.of(
                        6,
                        "7",
                        "ACTIVE",
                        LocalDateTime.now().toString(),
                        null,
                        null,
                        19
                )
        );
    }

    @DisplayName("답변 작성 테스트")
    @ParameterizedTest
    @MethodSource("getAnswer")
    void testCreateAnswer(
            int counselingQuestionNum, String counselingAnswerContent,
            AnswerState counselingAnswerState, String counselingAnswerInsertDatetime,
            String counselingAnswerDeleteDatetime, String counselingAnswerUpdateDatetime,
            int counselingAnswerReanswerNum) {
        //given
        AnswerDTO newAnswer = new AnswerDTO(
                counselingQuestionNum,
                counselingAnswerContent,
                counselingAnswerState,
                counselingAnswerInsertDatetime,
                counselingAnswerDeleteDatetime,
                counselingAnswerUpdateDatetime,
                counselingAnswerReanswerNum
        );

        //when
        // Mock file upload
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getOriginalFilename()).thenReturn("test.jpg");
        when(multipartFile.getSize()).thenReturn(1024L);

        //then
        Assertions.assertDoesNotThrow(
                () -> answerService.createAnswer(newAnswer, multipartFile)
        );
    }

    @DisplayName("답변 수정 테스트")
    @ParameterizedTest
    @MethodSource("getAnswer2")
    void testModifyAnswer(
            int counselingAnswerNum, int counselingQuestionNum,
            String counselingAnswerContent, AnswerState counselingAnswerState,
            String counselingAnswerInsertDatetime, String counselingAnswerDeleteDatetime,
            String counselingAnswerUpdateDatetime, int counselingAnswerReanswerNum) {
        //given
        AnswerDTO answer = new AnswerDTO(
                counselingAnswerNum,
                counselingQuestionNum,
                counselingAnswerContent,
                counselingAnswerState,
                counselingAnswerInsertDatetime,
                counselingAnswerDeleteDatetime,
                counselingAnswerUpdateDatetime,
                counselingAnswerReanswerNum
        );

        //when
        //then
        Assertions.assertDoesNotThrow(
                () -> answerService.modifyAnswer(answer)
        );
    }

    @DisplayName("답변 삭제 테스트")
    @ParameterizedTest
    @MethodSource("getAnswer3")
    void testRemoveAnswer(
            int counselingAnswerNum, int counselingQuestionNum,
            String counselingAnswerContent, AnswerState counselingAnswerState,
            String counselingAnswerInsertDatetime, String counselingAnswerDeleteDatetime,
            String counselingAnswerUpdateDatetime, int counselingAnswerReanswerNum) {
        //given
        AnswerDTO answer = new AnswerDTO(
                counselingAnswerNum,
                counselingQuestionNum,
                counselingAnswerContent,
                counselingAnswerState,
                counselingAnswerInsertDatetime,
                counselingAnswerDeleteDatetime,
                counselingAnswerUpdateDatetime,
                counselingAnswerReanswerNum
        );

        //when
        //then
        Assertions.assertDoesNotThrow(
                () -> answerService.removeAnswer(answer)
        );
    }

    @DisplayName("재답변 작성 테스트")
    @ParameterizedTest
    @MethodSource("getSubAnswer")
    void testCreateSubAnswer(
            int counselingQuestionNum, String counselingAnswerContent,
            AnswerState counselingAnswerState, String counselingAnswerInsertDatetime,
            String counselingAnswerDeleteDatetime, String counselingAnswerUpdateDatetime,
            int counselingAnswerReanswerNum) {
        //given
        AnswerDTO newSubAnswer = new AnswerDTO(
                counselingQuestionNum,
                counselingAnswerContent,
                counselingAnswerState,
                counselingAnswerInsertDatetime,
                counselingAnswerDeleteDatetime,
                counselingAnswerUpdateDatetime,
                counselingAnswerReanswerNum
        );

        //when
        // Mock file upload
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getOriginalFilename()).thenReturn("test.jpg");
        when(multipartFile.getSize()).thenReturn(1024L);

        //then
        Assertions.assertDoesNotThrow(
                () -> answerService.createSubAnswer(newSubAnswer, multipartFile)
        );
    }

}