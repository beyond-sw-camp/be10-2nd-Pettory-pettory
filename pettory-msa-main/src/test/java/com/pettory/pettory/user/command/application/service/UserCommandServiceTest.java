package com.pettory.pettory.user.command.application.service;

import com.pettory.pettory.exception.EmptyResultException;
import com.pettory.pettory.family.query.dto.ReceivedInvitationResponse;
import com.pettory.pettory.family.query.mapper.FamilyMapper;
import com.pettory.pettory.family.query.service.FamilyQueryService;
import com.pettory.pettory.family.query.service.InvitationQueryService;
import com.pettory.pettory.feedingRecord.query.dto.FeedingRecordDailyResponse;
import com.pettory.pettory.feedingRecord.query.dto.FeedingRecordSummaryResponse;
import com.pettory.pettory.feedingRecord.query.service.FeedingRecordQueryService;
import com.pettory.pettory.pet.query.dto.PetDTO;
import com.pettory.pettory.pet.query.service.PetQueryService;
import com.pettory.pettory.security.util.JwtUtil;
import com.pettory.pettory.user.command.application.dto.ChangePasswordRequest;
import com.pettory.pettory.user.command.application.dto.UserRegisterRequest;
import com.pettory.pettory.user.command.domain.aggregate.User;
import com.pettory.pettory.user.command.domain.aggregate.UserRole;
import com.pettory.pettory.user.command.domain.aggregate.UserState;
import com.pettory.pettory.user.command.domain.repository.UserRepository;
import com.pettory.pettory.user.query.dto.UserInfoResponse;
import com.pettory.pettory.user.query.mapper.UserMapper;
import com.pettory.pettory.user.query.service.UserQueryService;
import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordDailyResponse;
import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordSummaryResponse;
import com.pettory.pettory.walkingRecord.query.service.WalkingRecordQueryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserCommandServiceTest {

    @Autowired
    private UserQueryService userQueryService;

    private static Stream<Arguments> getRegisterUser() {
        return Stream.of(
                Arguments.of(
                        "pettory@gmail.com",
                        "*Aa!!yuriLK2",
                        "펫또리",
                        "박펫토리",
                        LocalDate.of(2001, 2, 13)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getRegisterUser")
    void testGetUserInfo(String userEmail, String userPassword, String userNickname, String userName, LocalDate userBirth) {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest(
                userEmail,
                userPassword,
                userNickname,
                userName,
                userBirth
        );

        Assertions.assertDoesNotThrow(() -> {
            userQueryService.getUserInfo(userRegisterRequest.getUserEmail());
        });
    }
























}