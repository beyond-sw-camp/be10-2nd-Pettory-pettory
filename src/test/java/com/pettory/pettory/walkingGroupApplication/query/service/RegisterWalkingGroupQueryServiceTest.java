package com.pettory.pettory.walkingGroupApplication.query.service;

import com.pettory.pettory.walkingGroupApplication.query.dto.RegisterWalkingGroupDetailResponse;
import com.pettory.pettory.walkingGroupApplication.query.dto.RegisterWalkingGroupListResponse;
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
class RegisterWalkingGroupQueryServiceTest {

    @Autowired
    private RegisterWalkingGroupQueryService registerWalkingGroupQueryService;

    private static Stream<Arguments> getRegisterWalkingGroups() {
        return Stream.of(
                Arguments.of(
                        1,
                        5,
                        1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getRegisterWalkingGroups")
    void testRegisterWalkingGroups(
            Integer page, Integer size, Integer walkingGroupId
    ) {
        Assertions.assertDoesNotThrow(
                () -> {
                    RegisterWalkingGroupListResponse response = registerWalkingGroupQueryService.getRegisterWalkingGroups(page, size, walkingGroupId);
                    response.getRegisterWalkingGroups().forEach(group -> System.out.println(group));
                }
        );
    }

    @Test
    void testRegisterWalkingGroup() {
        Assertions.assertDoesNotThrow(
                () -> {
                    RegisterWalkingGroupDetailResponse response = registerWalkingGroupQueryService.getRegisterWalkingGroup(1);
                    response.getRegisterWalkingGroup().forEach(group -> System.out.println(group));
                }
        );
    }

}