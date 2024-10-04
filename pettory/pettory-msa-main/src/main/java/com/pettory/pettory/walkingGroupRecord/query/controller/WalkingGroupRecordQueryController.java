package com.pettory.pettory.walkingGroupRecord.query.controller;

import com.pettory.pettory.walkingGroupRecord.query.dto.WalkingGroupRecordDetailResponse;
import com.pettory.pettory.walkingGroupRecord.query.dto.WalkingGroupRecordListResponse;
import com.pettory.pettory.walkingGroupRecord.query.service.WalkingGroupRecordQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walking-group-record")
public class WalkingGroupRecordQueryController {

    private final WalkingGroupRecordQueryService walkingGroupRecordQueryService;

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

    @GetMapping("/{walkingGroupId}")
    public ResponseEntity<WalkingGroupRecordDetailResponse> getWalkingGroupRecordById(
            @PathVariable int walkingGroupId
    ) {

        WalkingGroupRecordDetailResponse response = walkingGroupRecordQueryService.getWalkingGroupRecordById(walkingGroupId);

        return ResponseEntity.ok(response);

    }

}
