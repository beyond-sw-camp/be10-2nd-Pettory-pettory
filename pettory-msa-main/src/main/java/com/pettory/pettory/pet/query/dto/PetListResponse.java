package com.pettory.pettory.pet.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PetListResponse {
    private List<PetDTO> pets;
}
