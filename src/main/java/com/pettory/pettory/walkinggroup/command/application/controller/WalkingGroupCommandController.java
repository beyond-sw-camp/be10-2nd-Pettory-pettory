package com.pettory.pettory.walkinggroup.command.application.controller;

import com.pettory.pettory.walkinggroup.command.application.dto.WalkingGroupCreateRequest;
import com.pettory.pettory.walkinggroup.command.application.dto.WalkingGroupUpdateRequest;
import com.pettory.pettory.walkinggroup.command.application.service.WalkingGroupCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walkinggroup")
public class WalkingGroupCommandController {

    private final WalkingGroupCommandService walkingGroupCommandService;

    /* 신청 모임 등록 */
    @PostMapping("/")
    public ResponseEntity<Void> createWalkingGroup(@RequestBody @Valid WalkingGroupCreateRequest walkingGroupCreateRequest) {
        int walkingGroupId = walkingGroupCommandService.createWalkingGroup(walkingGroupCreateRequest);

        return ResponseEntity
                .created(URI.create("/api/walkinggroup/" + walkingGroupId))
                .build();
    }

    /* 산책 모임 수정 */
    @PutMapping("/{walkingGroupId}")
    public ResponseEntity<Void> updateWalkingGroup(
            @PathVariable int walkingGroupId,
            @RequestBody @Valid WalkingGroupUpdateRequest walkingGroupRequest
    ) {
        walkingGroupCommandService.updateWalkingGroup(walkingGroupId, walkingGroupRequest);

        return ResponseEntity.created(URI.create("/api/walkinggroup/" + walkingGroupId)).build();
    }

    /* 산책 모임 삭제 */
    @DeleteMapping("/{walkingGroupId}")
    public ResponseEntity<Void> deleteWalkingGroup(@PathVariable final int walkingGroupId) {

        walkingGroupCommandService.deleteWalkingGroup(walkingGroupId);

        return ResponseEntity.noContent().build();
    }



}
