package com.pettory.pettory.walkingGroupRecord.command.application.controller;

import com.pettory.pettory.walkingGroupRecord.command.application.dto.WalkingGroupRecordCreateRequest;
import com.pettory.pettory.walkingGroupRecord.command.application.dto.WalkingGroupRecordUpdateRequest;
import com.pettory.pettory.walkingGroupRecord.command.application.service.WalkingGroupRecordCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walking-group-record")
public class WalkingGroupRecordCommandController {

    private final WalkingGroupRecordCommandService walkingGroupRecordCommandService;

    @PostMapping("/")
    public ResponseEntity<Void> createWalkingGroupRecord(
            @RequestBody @Valid WalkingGroupRecordCreateRequest walkingGroupRecordRequest
    ) {

        int walkingGroupRecordId = walkingGroupRecordCommandService.createWalkingGroupRecord(
                walkingGroupRecordRequest
        );

        return ResponseEntity
                .created(URI.create("/api/walking-group-record/" + walkingGroupRecordId))
                .build();
    }

    @PutMapping("/{walkingGroupRecordId}")
    public ResponseEntity<Void> updateWalkingGroupRecord(
            @PathVariable int walkingGroupRecordId,
            @RequestBody @Valid WalkingGroupRecordUpdateRequest walkingGroupRequest
    ) {

        walkingGroupRecordCommandService.updateWalkingGroupRecord(walkingGroupRecordId, walkingGroupRequest);

        return ResponseEntity.created(URI.create("/api/walking-group-record/" + walkingGroupRecordId)).build();

    }

    @DeleteMapping("/{walkingGroupRecordId}")
    public ResponseEntity<Void> deleteWalkingGroupRecord(@PathVariable int walkingGroupRecordId) {

        walkingGroupRecordCommandService.deleteWalkingGroupRecord(walkingGroupRecordId);

        return ResponseEntity.noContent().build();
    }



}
