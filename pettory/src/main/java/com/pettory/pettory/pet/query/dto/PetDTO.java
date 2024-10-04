package com.pettory.pettory.pet.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PetDTO {
//    private Long petId;
    private String petType;
    private String petName;
    private String petBreed;
    private LocalDate petBirth;
    private String petGender;
    private Boolean petNeuteringYn;
    private Long petWeight;
    private String petMemo;
//    private LocalDateTime petInsertDatetime;
//    private LocalDateTime petUpdateDatetime;
//    private LocalDateTime petDeleteDatetime;
//    private String petState;
    // userId -- fk
    // familyId -- fk
}
