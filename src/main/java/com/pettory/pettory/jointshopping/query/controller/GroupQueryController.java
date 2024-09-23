package com.pettory.pettory.jointshopping.query.controller;

import com.pettory.pettory.jointshopping.query.dto.GroupDetailResponse;
import com.pettory.pettory.jointshopping.query.dto.GroupListResponse;
import com.pettory.pettory.jointshopping.query.service.GroupQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor    // final을 받은 필드의 생성자를 주입
@RequestMapping("/jointshopping")
public class GroupQueryController {

    private final GroupQueryService groupQueryService;

    @GetMapping("/group")
    public ResponseEntity<GroupListResponse> getGroups(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Long categoryNum,
            @RequestParam(required = false) String groupName
    ) {

        GroupListResponse response = groupQueryService.getGroups(page, size, categoryNum, groupName);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/group/{groupNum}")
    public ResponseEntity<GroupDetailResponse> getGroup(@PathVariable Long groupNum) {

        GroupDetailResponse response = groupQueryService.getGroup(groupNum);

        return ResponseEntity.ok(response);
    }

}
