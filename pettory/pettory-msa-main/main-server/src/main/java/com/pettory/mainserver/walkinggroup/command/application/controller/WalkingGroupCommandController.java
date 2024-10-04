package com.pettory.mainserver.walkinggroup.command.application.controller;

import com.pettory.mainserver.common.CommonResponseDTO;
import com.pettory.mainserver.walkinggroup.command.application.dto.WalkingGroupCreateRequest;
import com.pettory.mainserver.walkinggroup.command.application.dto.WalkingGroupUpdateRequest;
import com.pettory.mainserver.walkinggroup.command.application.service.WalkingGroupCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

        return ResponseEntity
                .created(URI.create("/api/walking-group/" + walkingGroupId))
                .build();
    }

    /* 산책 모임 수정 */
    @Operation(summary = "산책모임수정", description = "산책모임을 수정한다.")
    @PutMapping("/{walkingGroupId}")
    public ResponseEntity<CommonResponseDTO> updateWalkingGroup(
            @PathVariable int walkingGroupId,
            @RequestBody @Valid WalkingGroupUpdateRequest walkingGroupRequest
    ) {
        walkingGroupCommandService.updateWalkingGroup(walkingGroupId, walkingGroupRequest);

        return ResponseEntity.created(URI.create("/api/walking-group/" + walkingGroupId)).build();
    }

    /* 산책 모임 삭제 */
    @Operation(summary = "산책모임삭제", description = "산책모임을 삭제합니다.")
    @DeleteMapping("/{walkingGroupId}")
    public ResponseEntity<CommonResponseDTO> deleteWalkingGroup(@PathVariable final int walkingGroupId) {

        walkingGroupCommandService.deleteWalkingGroup(walkingGroupId);

        return ResponseEntity.noContent().build();
    }



}
