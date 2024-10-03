package com.pettory.pettory.walkingRecord.query.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WalkingRecordQueryServiceTest {

    @Autowired
    private WalkingRecordQueryService walkingRecordQueryService;

    private static Stream<Arguments> getWalkingRecordTestData() {
        return Stream.of(
                Arguments.of("ju@naver.com", 2024, 10, 1L, "self"),
                Arguments.of("ju@naver.com", 2024, 10, 1L, "family"),
                Arguments.of("ju@naver.com", 2024, 10, 1L, "all")
        );
    }

    private static Stream<Arguments> getWalkingRecordFamilyTestData() {
        return Stream.of(
                Arguments.of("ju@naver.com", 2024, 10, 1L, 100L)
        );
    }

    private static Stream<Arguments> getWalkingRecordAllFamilyTestData() {
        return Stream.of(
                Arguments.of("ju@naver.com", 2024, 10, 1L, 100L)
        );
    }

    @ParameterizedTest
    @MethodSource("getWalkingRecordTestData")
    void testGetWalkingRecordSummary(String userEmail, int year, int month, Long petId, String filterType) {

        Assertions.assertDoesNotThrow(() -> {
            walkingRecordQueryService.getWalkingRecordSummary(userEmail, year, month, petId, filterType);
        });
    }

    @ParameterizedTest
    @MethodSource("getWalkingRecordFamilyTestData")
    void testGetWalkingRecordsByFamilyExcludeUserAndMonth(String userEmail, int year, int month, Long petId, Long familyId) {

        Assertions.assertDoesNotThrow(() -> {
            walkingRecordQueryService.getWalkingRecordSummary(userEmail, year, month, petId, "family");
        });
    }

    @ParameterizedTest
    @MethodSource("getWalkingRecordAllFamilyTestData")
    void testFindAllWalkingRecordsByFamilyAndPetAndMonth(String userEmail, int year, int month, Long petId, Long familyId) {

        Assertions.assertDoesNotThrow(() -> {
            walkingRecordQueryService.getWalkingRecordSummary(userEmail, year, month, petId, "all");
        });
    }
}