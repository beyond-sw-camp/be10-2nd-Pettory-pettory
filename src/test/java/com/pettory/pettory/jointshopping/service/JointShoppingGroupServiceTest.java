package com.pettory.pettory.jointshopping.service;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupUserRequest;
import com.pettory.pettory.jointshopping.command.application.service.JointShoppingGroupApplicationService;
import com.pettory.pettory.jointshopping.query.service.JointShoppingGroupQueryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

@SpringBootTest
public class JointShoppingGroupServiceTest {

    @Autowired
    private JointShoppingGroupApplicationService jointShoppingGroupApplicationService;
    @Autowired
    private JointShoppingGroupQueryService jointShoppingGroupQueryService;

    private static Stream<Arguments> getGroup() {
        return Stream.of(
                Arguments.of(
                        1,
                        5,
                        null,
                        null,
                        null
                )
        );
    }

    private static Stream<Arguments> getGroupUser() {
        return Stream.of(
                Arguments.of(
                        1,
                        5,
                        1L
                )
        );
    }

    private static Stream<Arguments> getUserGroup() {
        return Stream.of(
                Arguments.of(
                        1,
                        5,
                        1L
                )
        );
    }

    private static Stream<Arguments> getCreateJointShoppingGroup() {
        return Stream.of(
                Arguments.of(
                        "상품 사실분 구합니다",
                        "강아지용품",
                        "강아지용품사실분",
                        100000,
                        1000,
                        10,
                        null,
                        2L,
                        4L,
                        new MockMultipartFile("files", "file2.txt", MediaType.TEXT_PLAIN_VALUE, "파일 내용 2".getBytes())
                )
        );
    }

    private static Stream<Arguments> getUpdateJointShoppingGroup() {
        return Stream.of(
                Arguments.of(
                        4L,
                        "상품 사실분 구합니다",
                        "강아지용품",
                        "강아지용품사실분구함구함구함",
                        100000,
                        1000,
                        10,
                        null,
                        2L,
                        4L,
                        null
                )
        );
    }

    private static Stream<Arguments> getInsertJointShoppingGroupUser() {
        return Stream.of(
                Arguments.of(
                        7L,
                        6L
                )
        );
    }

    /* 공동구매모임 등록 테스트 */
    @ParameterizedTest
    @MethodSource("getCreateJointShoppingGroup")
    void testCreateGroup(
            String jointShoppingGroupName, String jointShoppingProducts, String jointShoppingInfo,
            Integer jointShoppingCost, Integer jointShoppingGroupMaximumCount,
            Integer jointShoppingParticipationMaximumCount, String hostInvoiceNum,
            Long jointShoppingCategoryNum, Long userId, MultipartFile productImg
    ) {

        JointShoppingGroupRequest jointShoppingGroupRequest = new JointShoppingGroupRequest(
                jointShoppingGroupName,
                jointShoppingProducts,
                jointShoppingInfo,
                jointShoppingCost,
                jointShoppingGroupMaximumCount,
                jointShoppingParticipationMaximumCount,
                hostInvoiceNum,
                jointShoppingCategoryNum,
                userId
        );

        Assertions.assertDoesNotThrow(
                () -> jointShoppingGroupApplicationService.createGroup(jointShoppingGroupRequest, productImg)
        );
    }

    /* 공동구매모임 수정 테스트 */
    @ParameterizedTest
    @MethodSource("getUpdateJointShoppingGroup")
    void testUpdateGroup(
            Long jointShoppingGroupNum,
            String jointShoppingGroupName, String jointShoppingProducts, String jointShoppingInfo,
            Integer jointShoppingCost, Integer jointShoppingGroupMaximumCount,
            Integer jointShoppingParticipationMaximumCount, String hostInvoiceNum,
            Long jointShoppingCategoryNum, Long userId, MultipartFile productImg
    ) {

        JointShoppingGroupRequest jointShoppingGroupRequest = new JointShoppingGroupRequest(
                jointShoppingGroupName,
                jointShoppingProducts,
                jointShoppingInfo,
                jointShoppingCost,
                jointShoppingGroupMaximumCount,
                jointShoppingParticipationMaximumCount,
                hostInvoiceNum,
                jointShoppingCategoryNum,
                userId
        );

        Assertions.assertDoesNotThrow(
                () -> jointShoppingGroupApplicationService.updateGroup(jointShoppingGroupNum,jointShoppingGroupRequest, productImg)
        );
    }

    /* 공동구매모임 삭제 테스트 */
    @Test
    void testDeleteGroup() {
        Assertions.assertDoesNotThrow(
                () -> jointShoppingGroupApplicationService.deleteGroup(5L)
        );
    }

    /* 공동구매모임 참가(모임 사용자 등록) 테스트 */
    @ParameterizedTest
    @MethodSource("getInsertJointShoppingGroupUser")
    void testInsertGroupUser(
            Long jointShoppingGroupNum, Long userId
    ) {

        JointShoppingGroupUserRequest jointShoppingGroupUserRequest = new JointShoppingGroupUserRequest(
                jointShoppingGroupNum,
                userId
        );

        Assertions.assertDoesNotThrow(
                () -> jointShoppingGroupApplicationService.insertGroupUser(jointShoppingGroupUserRequest)
        );
    }

    /* 공동구매모임 나가기(모임 사용자 삭제) 테스트 */
    @Test
    void testExitGroupUser() {
        Assertions.assertDoesNotThrow(
                () -> jointShoppingGroupApplicationService.exitGroupUser(10L)
        );
    }

    /* 공동구매모임 강퇴 (모임 사용자 삭제, 재등록 불가 ) 테스트 */
    @Test
    void testWithdrawalGroupUser() {
        Assertions.assertDoesNotThrow(
                () -> jointShoppingGroupApplicationService.withdrawalGroupUser(5L)
        );
    }

    /* 공동구매모임 자동마감 테스트*/
    @Test
    void testCloseGroup() {
        Assertions.assertDoesNotThrow(
                () -> jointShoppingGroupApplicationService.closeGroup(7L)
        );
    }

    /* 공동구매모임 자동신청가능 테스트 */
    @Test
    void testApplyGroup() {
        Assertions.assertDoesNotThrow(
                () -> jointShoppingGroupApplicationService.applyGroup(11L)
        );
    }

    /* 공동구매모임 목록 조회 테스트 */
    @ParameterizedTest
    @MethodSource("getGroup")
    void testGetGroups(Integer page, Integer size, Long categoryNum, String groupName, String products) {

        Assertions.assertDoesNotThrow(
                () -> jointShoppingGroupQueryService.getGroups(page, size, categoryNum, groupName, products)
        );
    }

    /* 공동구매모임 상세 조회 테스트 */
    @Test
    void testGetGroup() {

        Assertions.assertDoesNotThrow(
                () -> jointShoppingGroupQueryService.getGroup(7L)
        );
    }

    /* 현재 공동구매모임의 전체 사용자 목록 조회 테스트 */
    @ParameterizedTest
    @MethodSource("getGroupUser")
    void testGetGroupUsers(Integer page, Integer size, Long groupNum) {

        Assertions.assertDoesNotThrow(
                () -> jointShoppingGroupQueryService.getGroupUsers(page, size, groupNum)
        );
    }

    /* 현재 사용자가 참여한 공동구매모임 목록 조회 테스트 */
    @ParameterizedTest
    @MethodSource("getUserGroup")
    void testGetUserGroups(Integer page, Integer size, Long userId) {

        Assertions.assertDoesNotThrow(
                () -> jointShoppingGroupQueryService.getUserGroups(page, size, userId)
        );
    }
}
