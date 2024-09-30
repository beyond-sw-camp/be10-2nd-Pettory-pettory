package com.pettory.pettory.jointshopping.query.controller;

import com.pettory.pettory.jointshopping.query.dto.JointShoppingGroupDeliveryInfoResponse;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingParticipationDeliveryInfoResponse;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingUserListResponse;
import com.pettory.pettory.jointshopping.query.service.JointShoppingGroupQueryService;
import com.pettory.pettory.jointshopping.query.service.JointShoppingParticipationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor    // final을 받은 필드의 생성자를 주입
@RequestMapping("/jointshopping")
public class JointShoppingParticipationQueryController {

    private final JointShoppingParticipationQueryService jointShoppingParticipationQueryService;

    /* 현재 공동구매모임 참가의 전체 사용자 목록 조회 */
    @GetMapping("/participation")
    public ResponseEntity<JointShoppingUserListResponse> getParticipants(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @PathVariable Long groupNum
    ) {

        JointShoppingUserListResponse response = jointShoppingParticipationQueryService.getParticipants(page, size, groupNum);

        return ResponseEntity.ok(response);
    }

    /*  공동구매 물품 배송 정보 조회(참가자) */
    @GetMapping("/participation/delivery-info/{participationNum}")
    public ResponseEntity<JointShoppingParticipationDeliveryInfoResponse> getDeliveryInfo(@PathVariable Long participationNum) {

        JointShoppingParticipationDeliveryInfoResponse response = jointShoppingParticipationQueryService.getDeliveryInfo(participationNum);

        return ResponseEntity.ok(response);
    }
}
