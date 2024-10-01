package com.pettory.pettory.jointshopping.query.controller;

import com.pettory.pettory.jointshopping.query.dto.JointShoppingGroupDetailResponse;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingGroupListResponse;
import com.pettory.pettory.jointshopping.query.dto.JointShoppingUserListResponse;
import com.pettory.pettory.jointshopping.query.service.JointShoppingGroupQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor    // final을 받은 필드의 생성자를 주입
@RequestMapping("/jointshopping")
public class JointShoppingGroupQueryController {

    private final JointShoppingGroupQueryService jointShoppingGroupQueryService;

    /* 공동구매모임 전체 조회 */
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

    /* 공동구매모임 상세 조회 */
    @GetMapping("/groups/{groupNum}")
    public ResponseEntity<JointShoppingGroupDetailResponse> getGroup(@PathVariable Long groupNum) {

        JointShoppingGroupDetailResponse response = jointShoppingGroupQueryService.getGroup(groupNum);

        return ResponseEntity.ok(response);
    }

    /* 즐겨찾기된 모임 목록 조회 */
    @GetMapping("/bookmarks")
    public ResponseEntity<JointShoppingGroupListResponse> getBookmarks(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Long userId
    ) {

        JointShoppingGroupListResponse response = jointShoppingGroupQueryService.getBookmarks(page, size, userId);

        return ResponseEntity.ok(response);
    }

    /* 현재 공동구매모임의 전체 사용자 목록 조회 */
    @GetMapping("/groups/users/{groupNum}")
    public ResponseEntity<JointShoppingUserListResponse> getGroupUsers(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @PathVariable Long groupNum
    ) {

        JointShoppingUserListResponse response = jointShoppingGroupQueryService.getGroupUsers(page, size, groupNum);

        return ResponseEntity.ok(response);
    }

    /* 현재 사용자가 참여한 공동구매모임 목록 조회 */
    @GetMapping("/groups/user")
    public ResponseEntity<JointShoppingGroupListResponse> getUserGroups(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam Long userId
    ) {

        JointShoppingGroupListResponse response = jointShoppingGroupQueryService.getUserGroups(page, size, userId);

        return ResponseEntity.ok(response);
    }

}
