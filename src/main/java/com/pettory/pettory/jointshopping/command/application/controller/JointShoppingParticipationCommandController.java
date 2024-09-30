package com.pettory.pettory.jointshopping.command.application.controller;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingDeliveryInfoRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingParticipationRequest;
import com.pettory.pettory.jointshopping.command.application.service.JointShoppingParticipationApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("jointshopping")
public class JointShoppingParticipationCommandController {

    private final JointShoppingParticipationApplicationService jointShoppingParticipationApplicationService;

    /* 공동구매 참가 등록 */
    @PostMapping("/participation")
    public ResponseEntity<Void> createParticipation(
            @RequestBody @Valid JointShoppingParticipationRequest participationRequest
    ) {
        Long participationNum = jointShoppingParticipationApplicationService.createParticipation(participationRequest);

        return ResponseEntity
                .created(URI.create("/jointshopping/participation/" + participationNum))
                .build();
    }

    /* 공동구매 참가 취소 */
    @DeleteMapping("/participation/{participationNum}")
    public ResponseEntity<Void> deleteParticipation(@PathVariable final Long participationNum) {

        jointShoppingParticipationApplicationService.deleteParticipation(participationNum);

        return ResponseEntity.noContent().build();
    }

    /* 공동구매 참가자 물품 배송 정보 등록(수정) */
    @PutMapping("/participation/delivery-info/{participationNum}")
    public ResponseEntity<Void> updateDeliveryInfo(
            @PathVariable final Long participationNum,
            @RequestBody @Valid JointShoppingDeliveryInfoRequest jointShoppingDeliveryInfoRequest
    ) {

        jointShoppingParticipationApplicationService.updateDeliveryInfo(participationNum, jointShoppingDeliveryInfoRequest);

        return ResponseEntity.created(URI.create("/jointshopping/participation/delivery-infonfo/" + participationNum)).build();
    }

    /*  공동구매 참가자 물품 수령으로 변경 */
    @PutMapping("/participation/products-receipt/{participationNum}")
    public ResponseEntity<Void> updateProductsReceipt(
            @PathVariable final Long participationNum
    ) {

        jointShoppingParticipationApplicationService.updateProductsReceipt(participationNum);

        return ResponseEntity.created(URI.create("/jointshopping/participation/products-receipt/" + participationNum)).build();
    }

}
