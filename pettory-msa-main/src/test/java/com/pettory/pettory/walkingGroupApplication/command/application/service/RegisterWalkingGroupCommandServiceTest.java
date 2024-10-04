package com.pettory.pettory.walkingGroupApplication.command.application.service;

import com.pettory.pettory.walkingGroupApplication.command.application.dto.RegisterWalkingGroupUpdateRequest;
import com.pettory.pettory.walkingGroupApplication.command.application.dto.WalkingGroupApplicationRequest;
import com.pettory.pettory.walkingGroupApplication.command.domain.aggregate.RegisterWalkingGroupState;
import com.pettory.pettory.walkingGroupApplication.command.domain.aggregate.WalkingGroupApprovalState;
import com.pettory.pettory.walkinggroup.command.application.service.WalkingGroupCommandService;
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
class RegisterWalkingGroupCommandServiceTest {

    @Autowired
    private RegisterWalkingGroupCommandService registerWalkingGroupCommandService;

    private static Stream<Arguments> getModifyRegisterWalkingGroup() {
        return Stream.of(
                Arguments.of(
                        1,
                        "RESIGN"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getModifyRegisterWalkingGroup")
    void testModifyRegisterWalkingGroup(
            int registerWalkingGroupId,
            String registerWalkingGroupState
    ) {
        RegisterWalkingGroupUpdateRequest registerWalkingGroupRequest = new RegisterWalkingGroupUpdateRequest(
                registerWalkingGroupState
        );

        Assertions.assertDoesNotThrow(
                () -> registerWalkingGroupCommandService.updateRegisterWalkingGroup(registerWalkingGroupId, registerWalkingGroupRequest)
        );
    }

    @Test
    void testDeleteRegisterWalkingGroup() {
        Assertions.assertDoesNotThrow(
                () -> registerWalkingGroupCommandService.deleteRegisterWalkingGroup(1)
        );
    }

}