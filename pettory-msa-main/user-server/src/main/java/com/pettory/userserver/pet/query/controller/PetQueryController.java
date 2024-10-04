package com.pettory.userserver.pet.query.controller;

import com.pettory.userserver.pet.command.application.service.PetCommandService;
import com.pettory.userserver.pet.query.dto.PetDTO;
import com.pettory.userserver.pet.query.dto.PetListResponse;
import com.pettory.userserver.pet.query.service.PetQueryService;
import com.pettory.userserver.security.util.UserSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(name = "Pettory 반려동물 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/pets")
public class PetQueryController {

    private final PetQueryService petQueryService;
    private final PetCommandService petCommandService;

    @Operation(summary = "반려동물 정보 조회", description = "기존 반려동물의 정보를 조회한다.")
    // 회원 자신과 자신이 속한 가족이 관리하는 반려동물 조회
    @GetMapping
    public ResponseEntity<PetListResponse> getAllPets() {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        List<PetDTO> pets = petQueryService.getAllPetsByUserId(currentUserEmail);
        PetListResponse response = PetListResponse.builder().pets(pets).build();
        return ResponseEntity.ok(response);
    }
}
