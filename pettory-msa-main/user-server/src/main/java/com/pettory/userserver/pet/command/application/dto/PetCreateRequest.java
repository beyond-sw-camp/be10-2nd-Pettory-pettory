package com.pettory.userserver.pet.command.application.dto;

import com.pettory.userserver.pet.command.domain.aggregate.PetGender;
import com.pettory.userserver.pet.command.domain.aggregate.PetType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class PetCreateRequest {
    @Schema(example = "DOG")
    @NotBlank
    private PetType petType;

    @Schema(example = "코그멍")
    @NotBlank
    private String petName;

    @Schema(example = "불독")
    @NotBlank
    private String petBreed;

    @Schema(example = "2010-06-24")
    private LocalDate petBirth;

    @Schema(example = "MALE")
    @NotBlank
    private PetGender petGender;

    @Schema(example = "true")
    @NotBlank
    private Boolean petNeuteringYn;

    @Schema(example = "10")
    @NotBlank
    private Long petWeight;

    @Schema(example = "몹시 귀여움")
    private String petMemo;
}
