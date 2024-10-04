package com.pettory.mainserver.walkinggroup.query.controller;

import com.pettory.mainserver.walkinggroup.query.dto.WalkingGroupDetailResponse;
import com.pettory.mainserver.walkinggroup.query.dto.WalkingGroupListResponse;
import com.pettory.mainserver.walkinggroup.query.service.WalkingGroupQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walking-group")
public class WalkingGroupQueryController {

    private final WalkingGroupQueryService walkingGroupQueryService;

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

    @GetMapping("/{walkingGroupId}")
    public ResponseEntity<WalkingGroupDetailResponse> getWalkingGroup(@PathVariable int walkingGroupId) {

        WalkingGroupDetailResponse response = walkingGroupQueryService.getWalkingGroup(walkingGroupId);

        return ResponseEntity.ok(response);

    }

}
