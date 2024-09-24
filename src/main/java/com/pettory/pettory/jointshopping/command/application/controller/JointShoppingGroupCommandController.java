package com.pettory.pettory.jointshopping.command.application.controller;

import com.pettory.pettory.jointshopping.command.application.dto.jointShoppingGroupCreateRequest;
import com.pettory.pettory.jointshopping.command.application.dto.jointShoppingGroupUpdateRequest;
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
    @PostMapping("/group")
    public ResponseEntity<Void> createGroup(
            @RequestPart @Valid jointShoppingGroupCreateRequest groupRequest,
            @RequestPart(required = false) MultipartFile productImg
    ) {

        Long jointShoppingGroupNum = jointShoppingGroupApplicationService.createGroup(groupRequest, productImg);

        return ResponseEntity
                .created(URI.create("/jointshopping/group/" + jointShoppingGroupNum))
                .build();
    }

    /* 공동구매모임 수정 */
    @PutMapping("/group/{jointShoppingGroupNum}")
    public ResponseEntity<Void> updateGroup(
            @PathVariable Long jointShoppingGroupNum,
            @RequestPart @Valid jointShoppingGroupUpdateRequest groupRequest,
            @RequestPart(required = false) MultipartFile productImg
    ) {

        jointShoppingGroupApplicationService.updateGroup(jointShoppingGroupNum, groupRequest, productImg);

        return ResponseEntity.created(URI.create("/jointshopping/group/" + jointShoppingGroupNum)).build();

    }

    /* 상품 삭제 */
    @DeleteMapping("/group/{jointShoppingGroupNum}")
    public ResponseEntity<Void> deleteGroup(@PathVariable final Long jointShoppingGroupNum) {

        jointShoppingGroupApplicationService.deleteGroup(jointShoppingGroupNum);

        return ResponseEntity.noContent().build();
    }
}
