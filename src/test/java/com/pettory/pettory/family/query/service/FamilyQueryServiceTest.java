package com.pettory.pettory.family.query.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FamilyQueryServiceTest {

    @Autowired
    private FamilyQueryService familyQueryService;

    private static Stream<Arguments> getFamilyInfoData() {
        return Stream.of(
                Arguments.of(
                        "김지유님의 가족",
                        10,
                        "김지유"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getFamilyInfoData")
    void testGetFamilyInfo() {
        String userEmail = "ptry@gmail.com";

        Assertions.assertDoesNotThrow(() -> {
            familyQueryService.getFamilyInfo(userEmail);
        });
    }

}