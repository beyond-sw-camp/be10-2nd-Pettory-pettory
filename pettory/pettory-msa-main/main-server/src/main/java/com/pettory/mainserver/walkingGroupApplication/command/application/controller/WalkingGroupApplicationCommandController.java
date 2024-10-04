package com.pettory.mainserver.walkingGroupApplication.command.application.controller;

import com.pettory.mainserver.common.CommonResponseDTO;
import com.pettory.mainserver.walkingGroupApplication.command.application.dto.WalkingGroupApplicationCreateRequest;
import com.pettory.mainserver.walkingGroupApplication.command.application.dto.WalkingGroupApplicationUpdateRequest;
import com.pettory.mainserver.walkingGroupApplication.command.application.service.WalkingGroupApplicationCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "산책 모임 신청")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walking-group-application")
public class WalkingGroupApplicationCommandController {

    private final WalkingGroupApplicationCommandService walkingGroupApplicationCommandService;

    @Operation(summary = "산책 모임 신청", description = "산책 모임을 신청한다.")
    @PostMapping("/")
    public ResponseEntity<CommonResponseDTO> createWalkingGroupApplication(
            @RequestBody @Valid WalkingGroupApplicationCreateRequest walkingGroupApplicationRequest
    ) {

        int walkingGroupApplicationId = walkingGroupApplicationCommandService.createWalkingGroupApplication(walkingGroupApplicationRequest);

        return ResponseEntity
                .created(URI.create("api/walking-group-application/" + walkingGroupApplicationId))
                .build();
    }

    @Operation(summary = "산책모임신청상태수정", description = "산책 모임의 신청 상태를 수정한다.")
    @PutMapping("/{walkingGroupApplicationId}")
    public ResponseEntity<CommonResponseDTO> updateWalkingGroupApplication(
            @PathVariable int walkingGroupApplicationId,
            @RequestBody @Valid WalkingGroupApplicationUpdateRequest walkingGroupApplicationRequest
    ) {

        walkingGroupApplicationCommandService.updateWalkingGroupApplication(walkingGroupApplicationId, walkingGroupApplicationRequest);

        return ResponseEntity.created(URI.create("/api/walking-group-application/" + walkingGroupApplicationId)).build();

    }

    @Operation(summary = "산책모임신청삭제", description = "산책모임신청을 삭제한다.")
    @DeleteMapping("/{walkingGroupApplicationId}")
    public ResponseEntity<CommonResponseDTO> deleteWalkingGroupApplication(@PathVariable final int walkingGroupApplicationId) {

        walkingGroupApplicationCommandService.deleteWalkingGroupApplication(walkingGroupApplicationId);

        return ResponseEntity.noContent().build();

    }
}
