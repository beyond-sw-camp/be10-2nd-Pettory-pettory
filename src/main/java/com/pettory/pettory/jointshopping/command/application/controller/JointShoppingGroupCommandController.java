package com.pettory.pettory.jointshopping.command.application.controller;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingDeliveryInfoRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupRequest;
import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingGroupUserRequest;
import com.pettory.pettory.jointshopping.command.application.service.JointShoppingGroupApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("jointshopping")
public class JointShoppingGroupCommandController {

    private final JointShoppingGroupApplicationService jointShoppingGroupApplicationService;

    /* 공동구매모임 등록 */
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

    /* 공동구매모임 수정 */
    @PutMapping("/groups/{jointShoppingGroupNum}")
    public ResponseEntity<Void> updateGroup(
            @PathVariable final Long jointShoppingGroupNum,
            @RequestPart @Valid JointShoppingGroupRequest groupRequest,
            @RequestPart(required = false) MultipartFile productImg
    ) {

        jointShoppingGroupApplicationService.updateGroup(jointShoppingGroupNum, groupRequest, productImg);

        return ResponseEntity.created(URI.create("/jointshopping/groups/" + jointShoppingGroupNum)).build();

    }

    /* 공동구매모임 삭제 */
    @DeleteMapping("/groups/{jointShoppingGroupNum}")
    public ResponseEntity<Void> deleteGroup(@PathVariable final Long jointShoppingGroupNum) {

        jointShoppingGroupApplicationService.deleteGroup(jointShoppingGroupNum);

        return ResponseEntity.noContent().build();
    }

    /* 공동구매모임 참가(모임 사용자 등록) */
    @PostMapping("/groups/users")
    public ResponseEntity<Void> insertGroupUser(
            @RequestBody @Valid JointShoppingGroupUserRequest groupUserRequest
            ){

        Long jointShoppingGroupUserListNum = jointShoppingGroupApplicationService.insertGroupUser(groupUserRequest);

        return ResponseEntity
                .created(URI.create("/jointshopping/groups/users/" + jointShoppingGroupUserListNum))
                .build();
    }

    /* 공동구매모임 나가기 (모임 사용자 삭제) */
    @DeleteMapping("/groups/users/{jointShoppingGroupUserNum}")
    public ResponseEntity<Void> exitGroupUser(@PathVariable final Long jointShoppingGroupUserNum) {

        jointShoppingGroupApplicationService.exitGroupUser(jointShoppingGroupUserNum);

        return ResponseEntity.noContent().build();
    }

    /* 공동구매모임 강퇴 (모임 사용자 삭제, 재등록 불가 ) */
    @DeleteMapping("/groups/users/withdrawal/{jointShoppingGroupUserNum}")
    public ResponseEntity<Void> withdrawalGroupUser(@PathVariable final Long jointShoppingGroupUserNum){

        jointShoppingGroupApplicationService.withdrawalGroupUser(jointShoppingGroupUserNum);

        return ResponseEntity.noContent().build();
    }

    /* 공동구매 방장 물품 배송 정보 등록(수정) */
    @PutMapping("/groups/delivery-info/{jointShoppingGroupNum}")
    public ResponseEntity<Void> updateDeliveryInfo(
            @PathVariable final Long jointShoppingGroupNum,
            @RequestBody @Valid JointShoppingDeliveryInfoRequest jointShoppingDeliveryInfoRequest
    ) {

        jointShoppingGroupApplicationService.updateDeliveryInfo(jointShoppingGroupNum, jointShoppingDeliveryInfoRequest);

        return ResponseEntity.created(URI.create("/jointshopping/groups/delivery-info/" + jointShoppingGroupNum)).build();
    }

}
