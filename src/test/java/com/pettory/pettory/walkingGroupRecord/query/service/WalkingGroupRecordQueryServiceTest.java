package com.pettory.pettory.walkingGroupRecord.query.service;

import com.pettory.pettory.walkingGroupApplication.query.dto.WalkingGroupApplicationListResponse;
import com.pettory.pettory.walkingGroupRecord.query.dto.WalkingGroupRecordDetailResponse;
import com.pettory.pettory.walkingGroupRecord.query.dto.WalkingGroupRecordListResponse;
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
class WalkingGroupRecordQueryServiceTest {

    @Autowired
    private WalkingGroupRecordQueryService walkingGroupRecordQueryService;

    private static Stream<Arguments> getWalkingGroupRecords() {
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
    @MethodSource("getWalkingGroupRecords")
    void testWalkingGroupRecords(
            Integer page,
            Integer size,
            LocalDate walkingGroupDate,
            String walkingGroupRecordState
    ) {
        Assertions.assertDoesNotThrow(
                () -> {
                    WalkingGroupRecordListResponse response = walkingGroupRecordQueryService.getWalkingGroupRecords(page, size, walkingGroupDate, walkingGroupRecordState);
                    response.getWalkingGroupRecords().forEach(group -> System.out.println(group));
                }
        );
    }

    @Test
    void testWalkingGroupRecord() {
        Assertions.assertDoesNotThrow(
                () -> {
                    WalkingGroupRecordDetailResponse response = walkingGroupRecordQueryService.getWalkingGroupRecordById(1);
                    response.getWalkingGroupRecord().forEach(group -> System.out.println(group));
                }
        );
    }

}