package com.pettory.pettory.walkinggroup.query.service;

import com.pettory.pettory.walkinggroup.command.domain.aggregate.WalkingGroupState;
import com.pettory.pettory.walkinggroup.query.dto.WalkingGroupDetailResponse;
import com.pettory.pettory.walkinggroup.query.dto.WalkingGroupListResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WalkingGroupQueryServiceTest {

    @Autowired
    private WalkingGroupQueryService walkingGroupQueryService;

    private static Stream<Arguments> getWalkingGroups() {
        return Stream.of(
                Arguments.of(
                        1,
                        5,
                        null,
                        null
                )
        );
    }


    @ParameterizedTest
    @MethodSource("getWalkingGroups")
    void testGetWalkingGroups(
            Integer page,
            Integer size,
            String walkingGroupName,
            String walkingGroupInfo
    ) {
        Assertions.assertDoesNotThrow(
                () -> {
                    WalkingGroupListResponse response = walkingGroupQueryService.getWalkingGroups(page, size, walkingGroupName, walkingGroupInfo);
                    response.getWalkingGroups().forEach(walkingGroup -> System.out.println(walkingGroup));
                }
        );

    }

    @Test
    void testGetWalkingGroup() {
        Assertions.assertDoesNotThrow(
                () -> {
                    WalkingGroupDetailResponse response = walkingGroupQueryService.getWalkingGroup(2);
                    System.out.println(response.getWalkingGroupsById());
                }
        );
    }

}