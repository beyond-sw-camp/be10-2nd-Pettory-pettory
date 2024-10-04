package com.pettory.pettory.feedingRecord.query.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FeedingRecordQueryServiceTest {

    @Autowired
    private FeedingRecordQueryService feedingRecordQueryService;

    private static Stream<Arguments> getFeedingRecordsByUserIdTestData() {
        return Stream.of(
                Arguments.of("ju@naver.com", 2024, 10, 1L)
        );
    }

    @ParameterizedTest
    @MethodSource("getFeedingRecordsByUserIdTestData")
    void testFindFeedingRecordsByUserIdForPetAndMonth(String userEmail, int year, int month, Long petId) {

        Assertions.assertDoesNotThrow(() -> {
            feedingRecordQueryService.getFeedingRecordSummary(userEmail, year, month, petId);
        });
    }
}