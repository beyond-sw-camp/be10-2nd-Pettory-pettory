package com.pettory.pettory.walkingGroupRecord.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.walkingGroupRecord.command.application.dto.WalkingGroupRecordCreateRequest;
import com.pettory.pettory.walkingGroupRecord.command.application.dto.WalkingGroupRecordUpdateRequest;
import com.pettory.pettory.walkingGroupRecord.command.application.service.WalkingGroupRecordCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "산책모임산책기록")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walking-group-record")
public class WalkingGroupRecordCommandController {

    private final WalkingGroupRecordCommandService walkingGroupRecordCommandService;

    @Operation(summary = "산책기록생성", description = "산책모임의 산책 기록을 작성한다.")
    @PostMapping("/")
    public ResponseEntity<CommonResponseDTO> createWalkingGroupRecord(
            @RequestBody @Valid WalkingGroupRecordCreateRequest walkingGroupRecordRequest
    ) {

        int walkingGroupRecordId = walkingGroupRecordCommandService.createWalkingGroupRecord(
                walkingGroupRecordRequest
        );

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "산책 모임 기록 작성 성공", walkingGroupRecordId);
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @Operation(summary = "산책모임기록수정", description = "산책모임의 산책기록을 수정한다.")
    @PutMapping("/{walkingGroupRecordId}")
    public ResponseEntity<CommonResponseDTO> updateWalkingGroupRecord(
            @PathVariable int walkingGroupRecordId,
            @RequestBody @Valid WalkingGroupRecordUpdateRequest walkingGroupRequest
    ) {

        walkingGroupRecordCommandService.updateWalkingGroupRecord(walkingGroupRecordId, walkingGroupRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "산책 모임 기록 수정 성공", walkingGroupRequest);
        return ResponseEntity.ok(successResponse);

    }

    @Operation(summary = "산책모임기록삭제", description = "산책모임의 산책기록을 삭제한다.")
    @DeleteMapping("/{walkingGroupRecordId}")
    public ResponseEntity<CommonResponseDTO> deleteWalkingGroupRecord(@PathVariable int walkingGroupRecordId) {

        walkingGroupRecordCommandService.deleteWalkingGroupRecord(walkingGroupRecordId);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "산책 모임 기록 삭제", null);
        return ResponseEntity.ok(successResponse);
    }



}
