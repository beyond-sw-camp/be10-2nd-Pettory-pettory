package com.pettory.pettory.walkinggroup.command.application.service;

import com.pettory.pettory.walkinggroup.command.application.dto.WalkingGroupCreateRequest;
import com.pettory.pettory.walkinggroup.command.application.dto.WalkingGroupUpdateRequest;
import com.pettory.pettory.walkinggroup.query.service.WalkingGroupQueryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WalkingGroupCommandServiceTest {

    @Autowired
    private WalkingGroupCommandService walkingGroupCommandService;

    private static Stream<Arguments> getRegisterWalkingGroup() {
        return Stream.of(
                Arguments.of(
                        "강아지와 돌기",
                        "강아지와 함께 산책합시다",
                        5,
                        1
                )
        );
    }

    private static Stream<Arguments> getModifyWalkingGroup() {
        return Stream.of(
                Arguments.of(
                        1,
                        "강아지와 아침부터 함께 산책",
                        "강아지와 함께 아침에 산책해요",
                        4,
                        "APPLICATION"

                )
        );
    }

    @ParameterizedTest
    @MethodSource("getRegisterWalkingGroup")
    void testRegisterWalkingGroup(
            String walkingGroupName,
            String walkingGroupInfo,
            int walkingGroupMaximumCount,
            int walkingGroupOwner
    ) {

        WalkingGroupCreateRequest walkingGroupRequest = new WalkingGroupCreateRequest(
                walkingGroupName,
                walkingGroupInfo,
                walkingGroupMaximumCount,
                walkingGroupOwner
        );

        Assertions.assertDoesNotThrow(
                () -> walkingGroupCommandService.createWalkingGroup(walkingGroupRequest)
        );
    }

    @ParameterizedTest
    @MethodSource("getModifyWalkingGroup")
    void testModifyWalkingGroup(
            int walkingGroupId,
            String walkingGroupName,
            String walkingGroupInfo,
            int walkingGroupMaximumCount,
            String walkingGroupState
    ) {
        WalkingGroupUpdateRequest walkingGroupRequest = new WalkingGroupUpdateRequest(
                walkingGroupName,
                walkingGroupInfo,
                walkingGroupMaximumCount,
                walkingGroupState
        );

        Assertions.assertDoesNotThrow(
                () -> walkingGroupCommandService.updateWalkingGroup(walkingGroupId, walkingGroupRequest)
        );
    }

    @Test
    void testDeleteWalkingGroup() {
        Assertions.assertDoesNotThrow(
                () -> walkingGroupCommandService.deleteWalkingGroup(1)
        );
    }


}