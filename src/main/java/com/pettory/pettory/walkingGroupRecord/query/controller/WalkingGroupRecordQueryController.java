package com.pettory.pettory.walkingGroupRecord.query.controller;

import com.pettory.pettory.walkingGroupRecord.query.dto.WalkingGroupRecordDetailResponse;
import com.pettory.pettory.walkingGroupRecord.query.dto.WalkingGroupRecordListResponse;
import com.pettory.pettory.walkingGroupRecord.query.service.WalkingGroupRecordQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "산책 모임 기록 컨트롤러", description = "산책 모임 기록 조회/산책 모임 기록 검색/특정 산책 모임 기록 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walking-group-record")
public class WalkingGroupRecordQueryController {

    private final WalkingGroupRecordQueryService walkingGroupRecordQueryService;

    @Operation(summary = "산책 모임 기록 조회", description = "산책 모임의 산책 기록을 조회한다.")
    @GetMapping("/all")
    public ResponseEntity<WalkingGroupRecordListResponse> getWalkingGroupRecords(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) LocalDate walkingGroupDate,
            @RequestParam(required = false) String walkingGroupRecordState

    ) {

        WalkingGroupRecordListResponse response = walkingGroupRecordQueryService.getWalkingGroupRecords(page, size, walkingGroupDate, walkingGroupRecordState);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "특정 산책 모임 기록 조회", description = "산책 모임 아이디를 입력받아 특정 산책 모임의 기록을 조회한다.")
    @GetMapping("/{walkingGroupId}")
    public ResponseEntity<WalkingGroupRecordDetailResponse> getWalkingGroupRecordById(
            @PathVariable int walkingGroupId
    ) {

        WalkingGroupRecordDetailResponse response = walkingGroupRecordQueryService.getWalkingGroupRecordById(walkingGroupId);

        return ResponseEntity.ok(response);

    }

}
