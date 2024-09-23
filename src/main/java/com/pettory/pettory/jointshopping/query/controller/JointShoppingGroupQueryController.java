package com.pettory.pettory.jointshopping.query.controller;

import com.pettory.pettory.jointshopping.query.dto.JointShoppingGroupDetailResponse;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingGroupListResponse;
import com.pettory.pettory.jointshopping.query.service.JointShoppingGroupQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor    // final을 받은 필드의 생성자를 주입
@RequestMapping("/jointshopping")
public class JointShoppingGroupQueryController {

    private final JointShoppingGroupQueryService jointShoppingGroupQueryService;

    @GetMapping("/group")
    public ResponseEntity<JointShoppingGroupListResponse> getGroups(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Long categoryNum,
            @RequestParam(required = false) String groupName
    ) {

        JointShoppingGroupListResponse response = jointShoppingGroupQueryService.getGroups(page, size, categoryNum, groupName);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/group/{groupNum}")
    public ResponseEntity<JointShoppingGroupDetailResponse> getGroup(@PathVariable Long groupNum) {

        JointShoppingGroupDetailResponse response = jointShoppingGroupQueryService.getGroup(groupNum);

        return ResponseEntity.ok(response);
    }

}
