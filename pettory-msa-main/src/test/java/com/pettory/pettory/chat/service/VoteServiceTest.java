package com.pettory.pettory.chat.service;

import com.pettory.pettory.chat.dto.vote.InsertVoteDTO;
import com.pettory.pettory.chat.dto.vote.ModifyVoteDTO;
import com.pettory.pettory.chat.dto.vote.SoftDeleteVoteDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@SpringBootTest
class VoteServiceTest {

    @Autowired
    VoteService voteService;

    private static Stream<Arguments> getVote() {
        return Stream.of(
                Arguments.of(
                        1,
                        3,
                        "투표테스트",
                        "다음과 같은 투표를 실시합니다!",
                        null,
                        "ACTIVE"
                )
        );
    }

    private static Stream<Arguments> getModifyVote() {
        return Stream.of(
                Arguments.of(
                        1,
                        3,
                        "투표 타이틀 수정 테스트",
                        "투표 내용을 수정합니다.",
                        null,
                        "MODIFY"
                )
        );
    }

    private static Stream<Arguments> getSoftDeleteVote() {
        return Stream.of(
                Arguments.of(
                        1,
                        null,
                        "DELETE"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getVote")
    void testInsertVote(int voteUniqueNum, int voteChatroomUniqueNum,
                        String voteTitle, String voteContent,
                        LocalDateTime voteInsertTime,
                        String voteState){
        voteInsertTime = LocalDateTime.now();

        InsertVoteDTO insertVoteDTO = new InsertVoteDTO(
                voteUniqueNum,
                voteChatroomUniqueNum,
                voteTitle,
                voteContent,
                voteInsertTime,
                voteState
        );

        Assertions.assertDoesNotThrow(
                () -> voteService.insertVote(insertVoteDTO)
        );
    }

    @ParameterizedTest
    @MethodSource("getModifyVote")
    void testModifyVote(int voteUniqueNum, int voteChatroomUniqueNum,
                        String voteTitle, String voteContent,
                        LocalDateTime voteUpdateTime,
                        String voteState) {
        voteUpdateTime = LocalDateTime.now();

        ModifyVoteDTO modifyVoteDTO = new ModifyVoteDTO(
                voteUniqueNum,
                voteChatroomUniqueNum,
                voteTitle,
                voteContent,
                voteUpdateTime,
                voteState
        );

        Assertions.assertDoesNotThrow(
                () -> voteService.modifyVote(modifyVoteDTO)
        );
    }

    @ParameterizedTest
    @MethodSource("getSoftDeleteVote")
    void testSoftDeleteVote(int voteUniqueNum, LocalDateTime deleteTime, String voteState) {
        deleteTime = LocalDateTime.now();
        SoftDeleteVoteDTO softDeleteVoteDTO = new SoftDeleteVoteDTO(
                voteUniqueNum,
                deleteTime,
                voteState
        );

        Assertions.assertDoesNotThrow(
                () -> voteService.softDeleteVote(softDeleteVoteDTO)
        );
    }

    @Test
    void testHardDeleteVote () {
        Assertions.assertDoesNotThrow(
                () -> voteService.hardDeleteVote(1)
        );
    }

    @Test
    void testSelectVoteList() {
        Assertions.assertDoesNotThrow(
                () -> voteService.selectVoteList(3)
        );
    }
}