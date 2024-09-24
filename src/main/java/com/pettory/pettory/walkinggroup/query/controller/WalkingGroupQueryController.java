package com.pettory.pettory.walkinggroup.query.controller;

import com.pettory.pettory.walkinggroup.query.dto.WalkingGroupDetailResponse;
import com.pettory.pettory.walkinggroup.query.dto.WalkingGroupListResponse;
import com.pettory.pettory.walkinggroup.query.service.WalkingGroupQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walkinggroup")
public class WalkingGroupQueryController {

    private final WalkingGroupQueryService walkingGroupQueryService;

    @GetMapping("/all")
    public ResponseEntity<WalkingGroupListResponse> getWalkingGroups(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String walkingGroupInfo
    ) {

        WalkingGroupListResponse response = walkingGroupQueryService.getWalkingGroups(page, size, walkingGroupInfo);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{walkingGroupName}")
    public ResponseEntity<WalkingGroupDetailResponse> getWalkingGroup(@PathVariable String walkingGroupName) {

        WalkingGroupDetailResponse response = walkingGroupQueryService.getWalkingGroup(walkingGroupName);

        return ResponseEntity.ok(response);

    }

}
