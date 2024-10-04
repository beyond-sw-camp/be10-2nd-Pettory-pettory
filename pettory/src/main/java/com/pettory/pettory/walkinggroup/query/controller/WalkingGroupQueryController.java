package com.pettory.pettory.walkinggroup.query.controller;

import com.pettory.pettory.walkinggroup.query.dto.WalkingGroupDetailResponse;
import com.pettory.pettory.walkinggroup.query.dto.WalkingGroupListResponse;
import com.pettory.pettory.walkinggroup.query.service.WalkingGroupQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "산책 모임 컨트롤러", description = "전체 산책 모임 조회/산책 모임 검색/특정 산책 모임 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walking-group")
public class WalkingGroupQueryController {

    private final WalkingGroupQueryService walkingGroupQueryService;

    @Operation(summary = "산책 모임 조회", description = "산책 모임을 조회한다. ")
    @GetMapping("/all")
    public ResponseEntity<WalkingGroupListResponse> getWalkingGroups(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String walkingGroupName,
            @RequestParam(required = false) String walkingGroupInfo
    ) {

        WalkingGroupListResponse response = walkingGroupQueryService.getWalkingGroups(page, size, walkingGroupName, walkingGroupInfo);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "특정 산책 모임 조회", description = "산책모임아이디로 특정 산책모임을 조회한다.")
    @GetMapping("/{walkingGroupId}")
    public ResponseEntity<WalkingGroupDetailResponse> getWalkingGroup(@PathVariable int walkingGroupId) {

        WalkingGroupDetailResponse response = walkingGroupQueryService.getWalkingGroup(walkingGroupId);

        return ResponseEntity.ok(response);

    }

}
