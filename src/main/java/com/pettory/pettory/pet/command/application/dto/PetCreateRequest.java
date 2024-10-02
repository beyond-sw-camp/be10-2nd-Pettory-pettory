package com.pettory.pettory.pet.command.application.dto;

import com.pettory.pettory.pet.command.domain.aggregate.PetGender;
import com.pettory.pettory.pet.command.domain.aggregate.PetType;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class PetCreateRequest {
    @NotBlank
    private PetType petType;
    @NotBlank
    private String petName;
    @NotBlank
    private String petBreed;
    private LocalDate petBirth;
    private PetGender petGender;
    @NotBlank
    private Boolean petNeuteringYn;
    @NotBlank
    private Long petWeight;
    private String petMemo;
}
