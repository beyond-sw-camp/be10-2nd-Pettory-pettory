package com.pettory.pettory.pet.command.mapper;

import com.pettory.pettory.pet.command.application.dto.PetCreateRequest;
import com.pettory.pettory.pet.command.domain.aggregate.Pet;

public class PetMapper {
    public static Pet toEntity(PetCreateRequest petCreateRequest) {
        return Pet.create(
                petCreateRequest.getPetType(),
                petCreateRequest.getPetName(),
                petCreateRequest.getPetBreed(),
                petCreateRequest.getPetBirth(),
                petCreateRequest.getPetGender(),
                petCreateRequest.getPetNeuteringYn(),
                petCreateRequest.getPetWeight(),
                petCreateRequest.getPetMemo()
        );
    }
}
