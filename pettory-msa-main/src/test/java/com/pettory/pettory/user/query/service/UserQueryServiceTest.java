package com.pettory.pettory.user.query.service;

import com.pettory.pettory.user.command.domain.aggregate.UserRole;
import com.pettory.pettory.user.command.domain.aggregate.UserState;
import com.pettory.pettory.user.query.dto.UserInfoResponse;
import org.junit.jupiter.api.Assertions;
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
class UserQueryServiceTest {

    @Autowired
    private UserQueryService userQueryService;

    private static Stream<Arguments> getUserInfoData() {
        return Stream.of(
                Arguments.of(
                        "ptry@gmail.com",
                        "펫톨이",
                        "박펫토리",
                        LocalDate.of(1990, 10, 3),
                        UserState.ACTIVE,
                        UserRole.USER,
                        true,
                        "펫병원",
                        "병원정보",
                        LocalDateTime.of(2024, 10, 3, 3, 20, 10),
                        null
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getUserInfoData")
    void testGetUserInfo(String userEmail, String userNickname, String userName, LocalDate userBirth, UserState userState, UserRole userRole, boolean userWalkingRecordPublicYn, String userHospitalName, String userHospitalInfo, LocalDateTime userRegisterDatetime, LocalDateTime userSuspensionEndDatetime) {

        UserInfoResponse userInfo = new UserInfoResponse(
                userEmail,
                userNickname,
                userName,
                userBirth,
                userState,
                userRole,
                userWalkingRecordPublicYn,
                userHospitalName,
                userHospitalInfo,
                userRegisterDatetime,
                userSuspensionEndDatetime
        );

        Assertions.assertDoesNotThrow(() -> {
            userQueryService.getUserInfo(userInfo.getUserEmail());
        });

    }

}