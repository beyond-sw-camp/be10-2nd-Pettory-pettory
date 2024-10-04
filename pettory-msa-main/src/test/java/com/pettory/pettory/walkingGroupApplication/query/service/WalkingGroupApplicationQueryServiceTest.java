package com.pettory.pettory.walkingGroupApplication.query.service;

import com.pettory.pettory.walkingGroupApplication.query.dto.WalkingGroupApplicationDetailResponse;
import com.pettory.pettory.walkingGroupApplication.query.dto.WalkingGroupApplicationListResponse;
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
class WalkingGroupApplicationQueryServiceTest {

    @Autowired
    private WalkingGroupApplicationQueryService walkingGroupApplicationQueryService;

    private static Stream<Arguments> getWalkingGroupApplications() {
        return Stream.of(
                Arguments.of(
                        1,
                        5,
                        null
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getWalkingGroupApplications")
    void testWalkingGroupApplications(
            Integer page, Integer size, String walkingGroupApprovalState
    ) {
        Assertions.assertDoesNotThrow(
                () -> {
                    WalkingGroupApplicationListResponse response = walkingGroupApplicationQueryService.getWalkingGroupApplications(page, size, walkingGroupApprovalState);
                    response.getWalkingGroupApplications().forEach(group -> System.out.println(group));
                }
        );
    }

    @Test
    void testWalkingGroupApplication() {
        Assertions.assertDoesNotThrow(
                () -> {
                    WalkingGroupApplicationDetailResponse response = walkingGroupApplicationQueryService.getWalkingGroupApplication(1);
                    response.getWalkingGroupApplicationById().forEach(group -> System.out.println(group));
                }
        );
    }

}