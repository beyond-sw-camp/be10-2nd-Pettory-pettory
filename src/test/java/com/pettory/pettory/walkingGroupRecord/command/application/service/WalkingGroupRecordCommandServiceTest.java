package com.pettory.pettory.walkingGroupRecord.command.application.service;

import com.pettory.pettory.walkingGroupRecord.command.application.dto.WalkingGroupRecordCreateRequest;
import com.pettory.pettory.walkingGroupRecord.command.application.dto.WalkingGroupRecordUpdateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WalkingGroupRecordCommandServiceTest {

    @Autowired
    private WalkingGroupRecordCommandService walkingGroupRecordCommandService;

    private static Stream<Arguments> getRegisterWalkingGroupRecord() {
        return Stream.of(
                Arguments.of(
                        LocalDate.of(2024, 10, 3),
                        "80",
                        "세빛둥둥섬",
                        "반포대교",
                        1
                )
        );
    }

    private static Stream<Arguments> getModifyWalkingGroupRecord() {
        return Stream.of(
                Arguments.of(
                        2,
                        LocalDate.of(2024, 10, 2),
                        "90",
                        "한강대교",
                        "마포대교",
                        "COMPLETE"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getRegisterWalkingGroupRecord")
    void testRegisterWalkingGroupRecord(
            LocalDate walkingGroupDate,
            int walkingGroupRecordDuration,
            String walkingGroupRouteStart,
            String walkingGroupRouteEnd,
            int walkingGroupId
    ) {
        WalkingGroupRecordCreateRequest request = new WalkingGroupRecordCreateRequest(
                walkingGroupDate,
                walkingGroupRecordDuration,
                walkingGroupRouteStart,
                walkingGroupRouteEnd,
                walkingGroupId
        );

        Assertions.assertDoesNotThrow(
                () -> walkingGroupRecordCommandService.createWalkingGroupRecord(request)
        );
    }

    @ParameterizedTest
    @MethodSource("getModifyWalkingGroupRecord")
    void testModifyWalkingGroupRecord(
            int walkingGroupRecordId,
            LocalDate walkingGroupDate,
            int walkingGroupRecordDuration,
            String walkingGroupRouteStart,
            String walkingGroupRouteEnd,
            String walkingGroupRecordState
    ) {
        WalkingGroupRecordUpdateRequest request = new WalkingGroupRecordUpdateRequest(
                walkingGroupDate,
                walkingGroupRecordDuration,
                walkingGroupRouteStart,
                walkingGroupRouteEnd,
                walkingGroupRecordState
        );

        Assertions.assertDoesNotThrow(
                () -> walkingGroupRecordCommandService.updateWalkingGroupRecord(walkingGroupRecordId, request)
        );
    }

    @Test
    void testDeleteWalkingGroupRecord() {
        Assertions.assertDoesNotThrow(
                () -> walkingGroupRecordCommandService.deleteWalkingGroupRecord(2)
        );
    }

}