package com.pettory.pettory.pet.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.pet.command.application.dto.PetCreateRequest;
import com.pettory.pettory.pet.command.application.dto.PetUpdateRequest;
import com.pettory.pettory.pet.command.application.service.PetCommandService;
import com.pettory.pettory.security.util.UserSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pettory 반려동물 컨트롤러", description = "반려동물 조회/등록/수정/삭제")
@RestController
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetCommandController {

    private final PetCommandService petCommandService;

    @Operation(summary = "반려동물 등록", description = "새로운 반려동물을 등록한다.")
    // 새 반려동물 등록
    @PostMapping
    public ResponseEntity<CommonResponseDTO> insertNewPet(@RequestBody PetCreateRequest petCreateRequest) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        Long newPetId = petCommandService.addNewPet(currentUserEmail, petCreateRequest);
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "새 반려동물 등록 성공", newPetId);

        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @Operation(summary = "반려동물 정보 수정", description = "기존에 등록된 반려동물의 정보를 수정한다.")
    // 반려동물 정보 수정
    @PutMapping("/{petId}")
    public ResponseEntity<CommonResponseDTO> modifyPet(
            @PathVariable Long petId,
            @RequestBody PetUpdateRequest petUpdateRequest) {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        petCommandService.updatePet(currentUserEmail, petId, petUpdateRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "반려동물 정보 수정 성공", null);
        return ResponseEntity.ok(successResponse);
    }

    @Operation(summary = "반려동물 정보 삭제", description = "기존의 반려동물 정볼를 삭제한다.")
    // 반려동물 정보 삭제
    @DeleteMapping("/{petId}")
    public ResponseEntity<CommonResponseDTO> deletePet(@PathVariable Long petId) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        petCommandService.deletePet(currentUserEmail, petId);
        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "반려동물 삭제 성공", null);
        return ResponseEntity.ok(successResponse);
    }


}
