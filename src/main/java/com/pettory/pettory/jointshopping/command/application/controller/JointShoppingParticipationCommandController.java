package com.pettory.pettory.jointshopping.command.application.controller;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingDeliveryInfoRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingParticipationRequest;
import com.pettory.pettory.jointshopping.command.application.service.JointShoppingParticipationApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@Tag(name = "Joint-Shopping-Participation")
@RestController
@RequiredArgsConstructor
@RequestMapping("jointshopping")
public class JointShoppingParticipationCommandController {

    private final JointShoppingParticipationApplicationService jointShoppingParticipationApplicationService;

    @Operation(summary = "공동구매 참가 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "공동구매 참가 성공"),
            @ApiResponse(responseCode = "400", description = "참가 불가"),
            @ApiResponse(responseCode = "404", description = "잘못된 공동구매모임 번호")
    })
    @PostMapping("/participation")
    public ResponseEntity<Void> createParticipation(
            @RequestBody @Valid JointShoppingParticipationRequest participationRequest
    ) {
        Long participationNum = jointShoppingParticipationApplicationService.createParticipation(participationRequest);

        return ResponseEntity
                .created(URI.create("/jointshopping/participation/" + participationNum))
                .build();
    }

    @Operation(summary = "공동구매 참가 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "공동구매 참가 삭제 성공"),
            @ApiResponse(responseCode = "400", description = "참가 취소 불가"),
            @ApiResponse(responseCode = "404", description = "잘못된 공동구매모임 번호 or 잘못된 공동구매 참가자 번호")
    })
    @DeleteMapping("/participation/{participationNum}")
    public ResponseEntity<Void> deleteParticipation(@PathVariable final Long participationNum) {

        jointShoppingParticipationApplicationService.deleteParticipation(participationNum);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "공동구매 참가자 물품 배송 정보 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "공동구매 참가자 물품 배송 정보 등록 성공"),
            @ApiResponse(responseCode = "404", description = "잘못된 공동구매 참가자 번호")
    })
    @PutMapping("/participation/delivery-info/{participationNum}")
    public ResponseEntity<Void> updateDeliveryInfo(
            @PathVariable final Long participationNum,
            @RequestBody @Valid JointShoppingDeliveryInfoRequest jointShoppingDeliveryInfoRequest
    ) {

        jointShoppingParticipationApplicationService.updateDeliveryInfo(participationNum, jointShoppingDeliveryInfoRequest);

        return ResponseEntity.created(URI.create("/jointshopping/participation/delivery-infonfo/" + participationNum)).build();
    }

    @Operation(summary = "공동구매 참가자 물품 수령으로 변경")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "공동구매 참가자 물품 수령으로 변경 성공"),
            @ApiResponse(responseCode = "404", description = "잘못된 공동구매모임 번호 or 잘못된 공동구매 참가자 번호")
    })
    @PutMapping("/participation/products-receipt/{participationNum}")
    public ResponseEntity<Void> updateProductsReceipt(
            @PathVariable final Long participationNum
    ) {

        jointShoppingParticipationApplicationService.updateProductsReceipt(participationNum);

        return ResponseEntity.created(URI.create("/jointshopping/participation/products-receipt/" + participationNum)).build();
    }

}
