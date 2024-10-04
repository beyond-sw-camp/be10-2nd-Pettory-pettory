package com.pettory.userserver.pet.command.mapper;

import com.pettory.userserver.pet.command.application.dto.PetCreateRequest;
import com.pettory.userserver.pet.command.domain.aggregate.Pet;

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
