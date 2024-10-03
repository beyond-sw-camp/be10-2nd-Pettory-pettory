package com.pettory.pettory.walkingGroupApplication.query.controller;

import com.pettory.pettory.walkingGroupApplication.query.dto.WalkingGroupApplicationDetailResponse;
import com.pettory.pettory.walkingGroupApplication.query.dto.WalkingGroupApplicationListResponse;
import com.pettory.pettory.walkingGroupApplication.query.service.WalkingGroupApplicationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "산책 모임 신청 컨트롤러", description = "산책 모임 신청 조회/산책 모임 신청 검색/특정 산책 모임 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walking-group-application")
public class WalkingGroupApplicationQueryController {

    private final WalkingGroupApplicationQueryService walkingGroupApplicationQueryService;

    @Operation(summary = "산책 모임 신청 조회", description = "산책 모임 신청을 조회한다.")
    @GetMapping("/all")
    public ResponseEntity<WalkingGroupApplicationListResponse> getWalkingGroupApplications(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String walkingGroupApprovalState
    ) {
        WalkingGroupApplicationListResponse response = walkingGroupApplicationQueryService.getWalkingGroupApplications(page, size, walkingGroupApprovalState);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "특정 산책 모임 신청 조회", description = "산책모임아이디를 입력받아 해당 산책모임의 신청을 조회한다.")
    @GetMapping("/{walkingGroupId}")
    public ResponseEntity<WalkingGroupApplicationDetailResponse> getWalkingGroupApplication(
            @PathVariable int walkingGroupId
    ) {
        WalkingGroupApplicationDetailResponse response = walkingGroupApplicationQueryService.getWalkingGroupApplication(walkingGroupId);
        
        return ResponseEntity.ok(response);
    }

}
