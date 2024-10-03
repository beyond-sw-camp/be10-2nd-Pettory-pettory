package com.pettory.pettory.jointshopping.query.controller;

import com.pettory.pettory.jointshopping.query.dto.*;
import com.pettory.pettory.jointshopping.query.service.JointShoppingGroupQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "공동구매모임")
@RestController
@RequiredArgsConstructor    // final을 받은 필드의 생성자를 주입
@RequestMapping("/jointshopping")
public class JointShoppingGroupQueryController {

    private final JointShoppingGroupQueryService jointShoppingGroupQueryService;

    @Operation(summary = "공동구매모임 전체 조회", description = "공동구매모임 전체 목록을 조회한다.")
    @ApiResponse(responseCode = "200", description = "공동구매모임 전체 조회 성공")
    @GetMapping("/groups")
    public ResponseEntity<JointShoppingGroupListResponse> getGroups(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Long categoryNum,
            @RequestParam(required = false) String groupName,
            @RequestParam(required = false) String products
    ) {

        JointShoppingGroupListResponse response = jointShoppingGroupQueryService.getGroups(page, size, categoryNum, groupName, products);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "공동구매모임 상세 조회", description = "공동구매모임을 조회한다.")
    @ApiResponse(responseCode = "200", description = "공동구매모임 상세 조회 성공")
    @GetMapping("/groups/{groupNum}")
    public ResponseEntity<JointShoppingGroupDetailResponse> getGroup(@PathVariable Long groupNum) {

        JointShoppingGroupDetailResponse response = jointShoppingGroupQueryService.getGroup(groupNum);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "현재 공동구매모임의 전체 사용자 목록 조회", description = "현재 공동구매모임의 전체 사용자 목록을 조회한다.")
    @ApiResponse(responseCode = "200", description = "현재 공동구매모임의 전체 사용자 목록 조회 성공")
    @GetMapping("/groups/users/{groupNum}")
    public ResponseEntity<JointShoppingUserListResponse> getGroupUsers(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @PathVariable @Schema(example = "1") Long groupNum
    ) {

        JointShoppingUserListResponse response = jointShoppingGroupQueryService.getGroupUsers(page, size, groupNum);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "현재 사용자가 참여한 공동구매모임 목록 조회", description = "현재 사용자가 참여한 공동구매모임 목록을 조회한다.")
    @ApiResponse(responseCode = "200", description = "현재 사용자가 참여한 공동구매모임 목록 조회 성공")
    @GetMapping("/groups/user")
    public ResponseEntity<JointShoppingGroupListResponse> getUserGroups(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam @Schema(example = "3") Long userId
    ) {

        JointShoppingGroupListResponse response = jointShoppingGroupQueryService.getUserGroups(page, size, userId);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "공동구매 물품 배송 정보 조회", description = "공동구매 물품 배송 정보를 조회한다.")
    @ApiResponse(responseCode = "200", description = "공동구매 물품 배송 정보 조회 성공")
    @GetMapping("/groups/delivery-info/{groupNum}")
    public ResponseEntity<JointShoppingGroupDeliveryInfoResponse> getDeliveryInfo(
            @PathVariable @Schema(example = "7") Long groupNum
    ) {

        JointShoppingGroupDeliveryInfoResponse response = jointShoppingGroupQueryService.getDeliveryInfo(groupNum);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "지급기록 조회", description = "지급기록을 조회한다.")
    @ApiResponse(responseCode = "200", description = "지급기록 조회 성공")
    @GetMapping("/provision-records")
    public ResponseEntity<ProvisionRecordResponse> getProvisionRecord(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String provisionState
    ){

        ProvisionRecordResponse response = jointShoppingGroupQueryService.getProvisionRecord(page, size, provisionState);

        return ResponseEntity.ok(response);
    }
}

