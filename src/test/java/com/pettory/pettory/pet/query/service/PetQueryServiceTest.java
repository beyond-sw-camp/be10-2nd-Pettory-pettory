package com.pettory.pettory.pet.query.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetQueryServiceTest {

    @Autowired
    private PetQueryService petQueryService;

    private static Stream<Arguments> getPetTestUser() {
        return Stream.of(
                Arguments.of("ju@naver.com")
        );
    }

    @ParameterizedTest
    @MethodSource("getPetTestUser")
    void testGetAllPetsByUserId(String userEmail) {

        Assertions.assertDoesNotThrow(() -> {
            petQueryService.getAllPetsByUserId(userEmail);
        });
    }

}