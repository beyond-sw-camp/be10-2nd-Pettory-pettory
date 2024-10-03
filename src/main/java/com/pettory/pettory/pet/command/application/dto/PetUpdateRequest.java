package com.pettory.pettory.pet.command.application.dto;

import com.pettory.pettory.pet.command.domain.aggregate.PetGender;
import com.pettory.pettory.pet.command.domain.aggregate.PetType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class PetUpdateRequest {
    @Schema(example = "DOG")
    private PetType petType;

    @Schema(example = "null")
    private String petName;

    @Schema(example = "null")
    private String petBreed;

    @Schema(example = "null")
    private LocalDate petBirth;

    @Schema(example = "MALE")
    private PetGender petGender;

    @Schema(example = "null")
    private Boolean petNeuteringYn;

    @Schema(example = "13")
    private Long petWeight;

    @Schema(example = "살쪘다ㅠㅠ")
    private String petMemo;
}
