package com.pettory.pettory.walkingGroupApplication.query.controller;

import com.pettory.pettory.walkingGroupApplication.query.dto.RegisterWalkingGroupDetailResponse;
import com.pettory.pettory.walkingGroupApplication.query.dto.RegisterWalkingGroupListResponse;
import com.pettory.pettory.walkingGroupApplication.query.service.RegisterWalkingGroupQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "가입한 산책 모임 컨트롤러", description = "가입한 산책 모임 조회/가입한 산책 모임 검색/특정 회원의 가입한 산책 모임 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register-walking-group")
public class RegisterWalkingGroupQueryController {

    private final RegisterWalkingGroupQueryService registerWalkingGroupQueryService;

    @Operation(summary = "가입한 산책 모임 조회", description = "가입한 산책 모임을 조회한다.")
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

    @Operation(summary = "특정 회원의 가입한 산책 모임 조회", description = "회원아이디로 가입한 산책 모임을 조회한다.")
    @GetMapping("/register-walking-groups/{userId}")
    public ResponseEntity<RegisterWalkingGroupDetailResponse> getRegisterWalkingGroup(
            @PathVariable int userId
    ) {

        RegisterWalkingGroupDetailResponse response = registerWalkingGroupQueryService.getRegisterWalkingGroup(userId);

        return ResponseEntity.ok(response);
    }
}
