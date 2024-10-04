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
class InvitationQueryServiceTest {

    @Autowired
    private InvitationQueryService invitationQueryService;

    private static Stream<Arguments> getInvitationTestUser() {
        return Stream.of(
                Arguments.of("ju@naver.com")
        );
    }

    @ParameterizedTest
    @MethodSource("getInvitationTestUser")
    void testGetReceivedInvitation(String userEmail) {

        Assertions.assertDoesNotThrow(() -> {
            invitationQueryService.getReceivedInvitation(userEmail);
        });
    }

}