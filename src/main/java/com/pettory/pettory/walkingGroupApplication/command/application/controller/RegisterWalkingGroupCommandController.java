package com.pettory.pettory.walkingGroupApplication.command.application.controller;

import com.pettory.pettory.walkingGroupApplication.command.application.dto.RegisterWalkingGroupUpdateRequest;
import com.pettory.pettory.walkingGroupApplication.command.application.dto.WalkingGroupApplicationRequest;
import com.pettory.pettory.walkingGroupApplication.command.application.service.RegisterWalkingGroupCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/register-walking-group")
public class RegisterWalkingGroupCommandController {

    private final RegisterWalkingGroupCommandService registerWalkingGroupCommandService;

    @PutMapping("/{walkingGroupApplicationId}/acceptance")
    public ResponseEntity<Void> acceptRegisterWalkingGroup(
            @PathVariable int walkingGroupApplicationId,
            @RequestBody @Valid WalkingGroupApplicationRequest walkingGroupApplicationRequest
    ) {

        registerWalkingGroupCommandService.acceptWalkingGroup(walkingGroupApplicationId, walkingGroupApplicationRequest);

        return ResponseEntity.created(URI.create("/api/register-walking-group/")).build();

    }

    @PutMapping("/{registerWalkingGroupId}")
    public ResponseEntity<Void> updateRegisterWalkingGroup(
            @PathVariable int registerWalkingGroupId,
            @RequestBody @Valid RegisterWalkingGroupUpdateRequest registerWalkingGroupUpdateRequest
    ) {

        registerWalkingGroupCommandService.updateRegisterWalkingGroup(
                registerWalkingGroupId, registerWalkingGroupUpdateRequest);

        return ResponseEntity.created(URI.create("/api/register-walking-group/" + registerWalkingGroupId)).build();

    }


    @DeleteMapping("/{registerWalkingGroupId}")
    public ResponseEntity<Void> deleteRegisterWalkingGroup(@PathVariable int registerWalkingGroupId) {

        registerWalkingGroupCommandService.deleteRegisterWalkingGroup(registerWalkingGroupId);

        return ResponseEntity.noContent().build();

    }

}
