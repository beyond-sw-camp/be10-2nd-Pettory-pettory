package com.pettory.pettory.walkinggroup.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.walkinggroup.command.application.dto.WalkingGroupCreateRequest;
import com.pettory.pettory.walkinggroup.command.application.dto.WalkingGroupUpdateRequest;
import com.pettory.pettory.walkinggroup.command.application.service.WalkingGroupCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "산책모임")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walking-group")
public class WalkingGroupCommandController {

    private final WalkingGroupCommandService walkingGroupCommandService;

    /* 신청 모임 등록 */
    @Operation(summary = "산책모임등록", description = "산책모임을 등록한다.")
    @PostMapping("/")
    public ResponseEntity<CommonResponseDTO> createWalkingGroup(@RequestBody @Valid WalkingGroupCreateRequest walkingGroupCreateRequest) {
        int walkingGroupId = walkingGroupCommandService.createWalkingGroup(walkingGroupCreateRequest);
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "새 산책모임 등록 성공", walkingGroupId);

        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    /* 산책 모임 수정 */
    @Operation(summary = "산책모임수정", description = "산책모임을 수정한다.")
    @PutMapping("/{walkingGroupId}")
    public ResponseEntity<CommonResponseDTO> updateWalkingGroup(
            @PathVariable int walkingGroupId,
            @RequestBody @Valid WalkingGroupUpdateRequest walkingGroupRequest
    ) {
        walkingGroupCommandService.updateWalkingGroup(walkingGroupId, walkingGroupRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "산책모임 정보 수정 성공", walkingGroupRequest);
        return ResponseEntity.ok(successResponse);
    }

    /* 산책 모임 삭제 */
    @Operation(summary = "산책모임삭제", description = "산책모임을 삭제합니다.")
    @DeleteMapping("/{walkingGroupId}")
    public ResponseEntity<CommonResponseDTO> deleteWalkingGroup(@PathVariable final int walkingGroupId) {

        walkingGroupCommandService.deleteWalkingGroup(walkingGroupId);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "산책모임 삭제 성공", null);
        return ResponseEntity.ok(successResponse);
    }



}
