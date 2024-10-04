package com.pettory.mainserver.walkingGroupRecord.command.application.controller;

import com.pettory.mainserver.common.CommonResponseDTO;
import com.pettory.mainserver.walkingGroupRecord.command.application.dto.WalkingGroupRecordCreateRequest;
import com.pettory.mainserver.walkingGroupRecord.command.application.dto.WalkingGroupRecordUpdateRequest;
import com.pettory.mainserver.walkingGroupRecord.command.application.service.WalkingGroupRecordCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

        return ResponseEntity
                .created(URI.create("/api/walking-group-record/" + walkingGroupRecordId))
                .build();
    }

    @Operation(summary = "산책모임기록수정", description = "산책모임의 산책기록을 수정한다.")
    @PutMapping("/{walkingGroupRecordId}")
    public ResponseEntity<CommonResponseDTO> updateWalkingGroupRecord(
            @PathVariable int walkingGroupRecordId,
            @RequestBody @Valid WalkingGroupRecordUpdateRequest walkingGroupRequest
    ) {

        walkingGroupRecordCommandService.updateWalkingGroupRecord(walkingGroupRecordId, walkingGroupRequest);

        return ResponseEntity.created(URI.create("/api/walking-group-record/" + walkingGroupRecordId)).build();

    }

    @Operation(summary = "산책모임기록삭제", description = "산책모임의 산책기록을 삭제한다.")
    @DeleteMapping("/{walkingGroupRecordId}")
    public ResponseEntity<CommonResponseDTO> deleteWalkingGroupRecord(@PathVariable int walkingGroupRecordId) {

        walkingGroupRecordCommandService.deleteWalkingGroupRecord(walkingGroupRecordId);

        return ResponseEntity.noContent().build();
    }



}
