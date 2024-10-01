package com.pettory.pettory.walkingGroupApplication.command.application.controller;

import com.pettory.pettory.walkingGroupApplication.command.application.dto.WalkingGroupApplicationCreateRequest;
import com.pettory.pettory.walkingGroupApplication.command.application.dto.WalkingGroupApplicationUpdateRequest;
import com.pettory.pettory.walkingGroupApplication.command.application.service.WalkingGroupApplicationCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/walking-group-application")
public class WalkingGroupApplicationCommandController {

    private final WalkingGroupApplicationCommandService walkingGroupApplicationCommandService;

    @PostMapping("/")
    public ResponseEntity<Void> createWalkingGroupApplication(
            @RequestBody @Valid WalkingGroupApplicationCreateRequest walkingGroupApplicationRequest
    ) {

        int walkingGroupApplicationId = walkingGroupApplicationCommandService.createWalkingGroupApplication(walkingGroupApplicationRequest);

        return ResponseEntity
                .created(URI.create("api/walking-group-application/" + walkingGroupApplicationId))
                .build();
    }

    @PutMapping("/{walkingGroupApplicationId}")
    public ResponseEntity<Void> updateWalkingGroupApplication(
            @PathVariable int walkingGroupApplicationId,
            @RequestBody @Valid WalkingGroupApplicationUpdateRequest walkingGroupApplicationRequest
    ) {

        walkingGroupApplicationCommandService.updateWalkingGroupApplication(walkingGroupApplicationId, walkingGroupApplicationRequest);

        return ResponseEntity.created(URI.create("/api/walking-group-application/" + walkingGroupApplicationId)).build();

    }

    @DeleteMapping("/{walkingGroupApplicationId}")
    public ResponseEntity<Void> deleteWalkingGroupApplication(@PathVariable final int walkingGroupApplicationId) {

        walkingGroupApplicationCommandService.deleteWalkingGroupApplication(walkingGroupApplicationId);

        return ResponseEntity.noContent().build();

    }
}
