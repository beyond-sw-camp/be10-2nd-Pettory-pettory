package com.pettory.mainserver.walkingGroupApplication.query.controller;

import com.pettory.mainserver.walkingGroupApplication.query.dto.RegisterWalkingGroupDetailResponse;
import com.pettory.mainserver.walkingGroupApplication.query.dto.RegisterWalkingGroupListResponse;
import com.pettory.mainserver.walkingGroupApplication.query.service.RegisterWalkingGroupQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register-walking-group")
public class RegisterWalkingGroupQueryController {

    private final RegisterWalkingGroupQueryService registerWalkingGroupQueryService;

    @GetMapping("/register-walking-groups")
    public ResponseEntity<RegisterWalkingGroupListResponse> getRegisterWalkingGroups(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Integer walkingGroupId
    ) {

        RegisterWalkingGroupListResponse response = registerWalkingGroupQueryService.getRegisterWalkingGroups(
                page, size, walkingGroupId);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/register-walking-groups/{userId}")
    public ResponseEntity<RegisterWalkingGroupDetailResponse> getRegisterWalkingGroup(
            @PathVariable int userId
    ) {

        RegisterWalkingGroupDetailResponse response = registerWalkingGroupQueryService.getRegisterWalkingGroup(userId);

        return ResponseEntity.ok(response);
    }
}
