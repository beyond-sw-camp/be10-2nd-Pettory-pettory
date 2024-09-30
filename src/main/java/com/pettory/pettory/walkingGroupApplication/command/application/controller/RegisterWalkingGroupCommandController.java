package com.pettory.pettory.walkingGroupApplication.command.application.controller;

import com.pettory.pettory.walkingGroupApplication.command.application.dto.RegisterWalkingGroupUpdateRequest;
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

    @PutMapping("/{registerWalkingGroupId}")
    public ResponseEntity<Void> updateRegisterWalkingGroup(
            @PathVariable String registerWalkingGroupId,
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
