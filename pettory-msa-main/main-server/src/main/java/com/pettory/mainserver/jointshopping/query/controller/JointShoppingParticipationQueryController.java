package com.pettory.mainserver.jointshopping.query.controller;

import com.pettory.mainserver.jointshopping.query.dto.JointShoppingParticipationDeliveryInfoResponse;
import com.pettory.mainserver.jointshopping.query.dto.JointShoppingUserListResponse;
import com.pettory.mainserver.jointshopping.query.service.JointShoppingParticipationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "공동구매 참가")
@RestController
@RequiredArgsConstructor    // final을 받은 필드의 생성자를 주입
@RequestMapping("/jointshopping")
public class JointShoppingParticipationQueryController {

    private final JointShoppingParticipationQueryService jointShoppingParticipationQueryService;

    @Operation(summary = "현재 공동구매모임 참가의 전체 사용자 목록 조회", description = "현재 공동구매모임 참가의 전체 사용자 목록을 조회한다.")
    @ApiResponse(responseCode = "200", description = "현재 공동구매모임 참가의 전체 사용자 목록 조회 성공")
    @GetMapping("/participation/{groupNum}")
    public ResponseEntity<JointShoppingUserListResponse> getParticipants(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @PathVariable @Schema(example = "7") Long groupNum
    ) {

        JointShoppingUserListResponse response = jointShoppingParticipationQueryService.getParticipants(page, size, groupNum);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "공동구매 물품 배송 정보 조회", description = "공동구매 물품 배송 정보을 조회한다.")
    @ApiResponse(responseCode = "200", description = "공동구매 물품 배송 정보 조회 성공")
    @GetMapping("/participation/delivery-info/{participationNum}")
    public ResponseEntity<JointShoppingParticipationDeliveryInfoResponse> getDeliveryInfo(
            @PathVariable @Schema(example = "12") Long participationNum
    ) {

        JointShoppingParticipationDeliveryInfoResponse response = jointShoppingParticipationQueryService.getDeliveryInfo(participationNum);

        return ResponseEntity.ok(response);
    }
}
