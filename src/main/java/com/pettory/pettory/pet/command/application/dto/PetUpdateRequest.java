package com.pettory.pettory.pet.command.application.dto;

import com.pettory.pettory.pet.command.domain.aggregate.PetGender;
import com.pettory.pettory.pet.command.domain.aggregate.PetType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class PetUpdateRequest {
    private PetType petType;
    private String petName;
    private String petBreed;
    private LocalDate petBirth;
    private PetGender petGender;
    private Boolean petNeuteringYn;
    private Long petWeight;
    private String petMemo;
}
