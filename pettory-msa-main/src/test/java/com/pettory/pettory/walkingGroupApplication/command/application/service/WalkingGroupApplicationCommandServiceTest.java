package com.pettory.pettory.walkingGroupApplication.command.application.service;

import com.pettory.pettory.walkingGroupApplication.command.application.dto.WalkingGroupApplicationCreateRequest;
import com.pettory.pettory.walkingGroupApplication.command.application.dto.WalkingGroupApplicationUpdateRequest;
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
class WalkingGroupApplicationCommandServiceTest {

    @Autowired
    private WalkingGroupApplicationCommandService walkingGroupApplicationCommandService;

    private static Stream<Arguments> getRegisterWalkingGroupApplication() {
        return Stream.of(
                Arguments.of(
                        2,
                        3
                )
        );
    }

    private static Stream<Arguments> getModifyWalkingGroupApplication() {
        return Stream.of(
                Arguments.of(
                        1,
                        "REFUSAL"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getRegisterWalkingGroupApplication")
    void testRegisterWalkingGroupApplication(
            int walkingGroupId, int userId
    ) {
        WalkingGroupApplicationCreateRequest request = new WalkingGroupApplicationCreateRequest(
                walkingGroupId, userId
        );

        Assertions.assertDoesNotThrow(
                () -> walkingGroupApplicationCommandService.createWalkingGroupApplication(request)
        );
    }

    @ParameterizedTest
    @MethodSource("getModifyWalkingGroupApplication")
    void testModifyWalkingGroupApplication(
            int walkingGroupApplicationId,
            String walkingGroupApprovalState
    ) {
        WalkingGroupApplicationUpdateRequest request = new WalkingGroupApplicationUpdateRequest(
                walkingGroupApprovalState
        );

        Assertions.assertDoesNotThrow(
                () -> walkingGroupApplicationCommandService.updateWalkingGroupApplication(walkingGroupApplicationId, request)
        );
    }

    @Test
    void testDeleteWalkingGroupApplication() {
        Assertions.assertDoesNotThrow(
                () -> walkingGroupApplicationCommandService.deleteWalkingGroupApplication(1)
        );
    }

}