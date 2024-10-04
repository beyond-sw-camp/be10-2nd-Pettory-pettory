package com.pettory.mainserver.walkingGroupApplication.query.controller;

import com.pettory.mainserver.walkingGroupApplication.query.dto.WalkingGroupApplicationDetailResponse;
import com.pettory.mainserver.walkingGroupApplication.query.dto.WalkingGroupApplicationListResponse;
import com.pettory.mainserver.walkingGroupApplication.query.service.WalkingGroupApplicationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walkingGroupApplication")
public class WalkingGroupApplicationQueryController {

    private final WalkingGroupApplicationQueryService walkingGroupApplicationQueryService;

    @GetMapping("/all")
    public ResponseEntity<WalkingGroupApplicationListResponse> getWalkingGroupApplications(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String walkingGroupApprovalState
    ) {
        WalkingGroupApplicationListResponse response = walkingGroupApplicationQueryService.getWalkingGroupApplications(page, size, walkingGroupApprovalState);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{walkingGroupId}")
    public ResponseEntity<WalkingGroupApplicationDetailResponse> getWalkingGroupApplication(
            @PathVariable int walkingGroupId
    ) {
        WalkingGroupApplicationDetailResponse response = walkingGroupApplicationQueryService.getWalkingGroupApplication(walkingGroupId);
        
        return ResponseEntity.ok(response);
    }

}
