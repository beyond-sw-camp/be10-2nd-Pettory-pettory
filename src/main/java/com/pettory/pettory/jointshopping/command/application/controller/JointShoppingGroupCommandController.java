package com.pettory.pettory.jointshopping.command.application.controller;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingDeliveryInfoRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupUserRequest;
import com.pettory.pettory.jointshopping.command.application.service.JointShoppingGroupApplicationService;
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

@Tag(name = "Joint-Shopping-Group")
@RestController
@RequiredArgsConstructor
@RequestMapping("jointshopping")
public class JointShoppingGroupCommandController {

    private final JointShoppingGroupApplicationService jointShoppingGroupApplicationService;

    @Operation(summary = "공동구매모임 등록")
    @ApiResponse(responseCode = "201", description = "공동구매모임 등록 성공")
    @PostMapping("/groups")
    public ResponseEntity<Void> createGroup(
            @RequestPart @Valid JointShoppingGroupRequest groupRequest,
            @RequestPart(required = false) MultipartFile productImg
    ) {

        Long jointShoppingGroupNum = jointShoppingGroupApplicationService.createGroup(groupRequest, productImg);

        return ResponseEntity
                .created(URI.create("/jointshopping/groups/" + jointShoppingGroupNum))
                .build();
    }

    @Operation(summary = "공동구매모임 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "공동구매모임 수정 성공"),
            @ApiResponse(responseCode = "404", description = "잘못된 공동구매모임 번호")
    })
    @PutMapping("/groups/{jointShoppingGroupNum}")
    public ResponseEntity<Void> updateGroup(
            @PathVariable final Long jointShoppingGroupNum,
            @RequestPart @Valid JointShoppingGroupRequest groupRequest,
            @RequestPart(required = false) MultipartFile productImg
    ) {

        jointShoppingGroupApplicationService.updateGroup(jointShoppingGroupNum, groupRequest, productImg);

        return ResponseEntity.created(URI.create("/jointshopping/groups/" + jointShoppingGroupNum)).build();

    }

    @Operation(summary = "공동구매모임 삭제")
    @ApiResponse(responseCode = "204", description = "공동구매모임 삭제 성공")
    @DeleteMapping("/groups/{jointShoppingGroupNum}")
    public ResponseEntity<Void> deleteGroup(@PathVariable final Long jointShoppingGroupNum) {

        jointShoppingGroupApplicationService.deleteGroup(jointShoppingGroupNum);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "공동구매모임 참가")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "공동구매모임 참가 성공"),
            @ApiResponse(responseCode = "400", description = "모임 참가 불가 or 이미 강퇴 당한 회원"),
            @ApiResponse(responseCode = "404", description = "잘못된 공동구매모임 번호")
    })
    @PostMapping("/groups/users")
    public ResponseEntity<Void> insertGroupUser(
            @RequestBody @Valid JointShoppingGroupUserRequest groupUserRequest
            ){

        Long jointShoppingGroupUserListNum = jointShoppingGroupApplicationService.insertGroupUser(groupUserRequest);

        return ResponseEntity
                .created(URI.create("/jointshopping/groups/users/" + jointShoppingGroupUserListNum))
                .build();
    }

    @Operation(summary = "공동구매모임 나가기")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "공동구매모임 나가기 성공"),
            @ApiResponse(responseCode = "404", description = "잘못된 공동구매모임 번호 or 잘못된 공동구매 참가자 번호")
    })
    @DeleteMapping("/groups/users/{jointShoppingGroupUserNum}")
    public ResponseEntity<Void> exitGroupUser(@PathVariable final Long jointShoppingGroupUserNum) {

        jointShoppingGroupApplicationService.exitGroupUser(jointShoppingGroupUserNum);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "공동구매모임 강퇴")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "공동구매모임 강퇴 성공"),
            @ApiResponse(responseCode = "404", description = "잘못된 공동구매모임 번호 or 잘못된 공동구매 참가자 번호")
    })
    @DeleteMapping("/groups/users/withdrawal/{jointShoppingGroupUserNum}")
    public ResponseEntity<Void> withdrawalGroupUser(@PathVariable final Long jointShoppingGroupUserNum){

        jointShoppingGroupApplicationService.withdrawalGroupUser(jointShoppingGroupUserNum);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "공동구매 방장 물품 배송 정보 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "공동구매 방장 물품 배송 정보 성공"),
            @ApiResponse(responseCode = "400", description = "배송 정보 입력 불가"),
            @ApiResponse(responseCode = "404", description = "잘못된 공동구매모임 번호")
    })
    @PutMapping("/groups/delivery-info/{jointShoppingGroupNum}")
    public ResponseEntity<Void> updateDeliveryInfo(
            @PathVariable final Long jointShoppingGroupNum,
            @RequestBody @Valid JointShoppingDeliveryInfoRequest jointShoppingDeliveryInfoRequest
    ) {

        jointShoppingGroupApplicationService.updateDeliveryInfo(jointShoppingGroupNum, jointShoppingDeliveryInfoRequest);

        return ResponseEntity.created(URI.create("/jointshopping/groups/delivery-info/" + jointShoppingGroupNum)).build();
    }

}
