package com.pettory.userserver.pet.command.application.service;

import com.pettory.userserver.exception.AlreadyDeletedException;
import com.pettory.userserver.exception.NotFoundException;
import com.pettory.userserver.exception.UnauthorizedException;
import com.pettory.userserver.pet.command.application.dto.PetCreateRequest;
import com.pettory.userserver.pet.command.application.dto.PetUpdateRequest;
import com.pettory.userserver.pet.command.domain.aggregate.Pet;
import com.pettory.userserver.pet.command.domain.aggregate.PetAccess;
import com.pettory.userserver.pet.command.domain.aggregate.PetState;
import com.pettory.userserver.pet.command.domain.repository.PetAccessRepository;
import com.pettory.userserver.pet.command.domain.repository.PetRepository;
import com.pettory.userserver.pet.command.infrastructure.repository.JpaPetRepository;
import com.pettory.userserver.pet.command.mapper.PetMapper;
import com.pettory.userserver.security.util.UserSecurity;
import com.pettory.userserver.user.command.domain.aggregate.User;
import com.pettory.userserver.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetCommandService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;
    private final PetAccessRepository petAccessRepository;
    private final JpaPetRepository jpaPetRepository;

    // 새로운 반려동물 등록
    @Transactional
    public Long addNewPet(String userEmail, PetCreateRequest petCreateRequest) {

        UserSecurity.validateCurrentUser(userEmail);

        // 회원 정보 확인
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        Pet newPet = PetMapper.toEntity(petCreateRequest);

        // 회원 엔터티에 반려동물 추가
        newPet.addPetToUser(user);

        // 회원이 가족에 속해있다면 반려동물에 가족 id 설정
        if (user.getFamily() != null) {
            newPet.addPetToFamily(user.getFamily());
        }

        // 반려동물 정보 저장
        Pet savedPet = petRepository.save(newPet);

        // 반려동물 권한 저장
        PetAccess newPetAccess = PetAccess.createPetAccess(user, newPet);
        petAccessRepository.save(newPetAccess);

        return savedPet.getPetId();
    }

    // 반려동물 정보 수정
    @Transactional
    public void updatePet(String userEmail, Long petId, PetUpdateRequest petUpdateRequest) {

        UserSecurity.validateCurrentUser(userEmail);

        // 회원 정보 확인
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        // 반려동물 조회
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("반려동물이 존재하지 않습니다."));

//        if (!pet.getUser().getUserId().equals(user.getUserId())) {
//            // 해당 반려동물을 가족 구성원이 등록한 것인지 확인
//            if (user.getFamily() == null
//                    || pet.getFamily() == null
//                    || !user.getFamily().getFamilyId().equals(pet.getFamily().getFamilyId())) {
//                throw new UnauthorizedException("해당 반려동물에 접근할 권한이 없습니다.");
//            }
//        }

        if (!pet.getUser().getUserId().equals(user.getUserId())) {
            throw new UnauthorizedException("해당 반려동물 정보를 수정할 권한이 없습니다.");
        }

        // 반려동물 정보 수정
        pet.updatePetInfo(
                petUpdateRequest.getPetType(),
                petUpdateRequest.getPetName(),
                petUpdateRequest.getPetBreed(),
                petUpdateRequest.getPetBirth(),
                petUpdateRequest.getPetGender(),
                petUpdateRequest.getPetNeuteringYn(),
                petUpdateRequest.getPetWeight(),
                petUpdateRequest.getPetMemo()
        );
    }

    // 반려동물 정보 삭제
    @Transactional
    public void deletePet(String userEmail, Long petId) {

        UserSecurity.validateCurrentUser(userEmail);

        // 회원 정보 확인
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));


        // 반려동물 조회
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("반려동물이 존재하지 않습니다."));

//        if (!pet.getUser().getUserId().equals(user.getUserId())) {
//            // 해당 반려동물을 가족 구성원이 등록한 것인지 확인
//            if (user.getFamily() == null
//                    || pet.getFamily() == null
//                    || !user.getFamily().getFamilyId().equals(pet.getFamily().getFamilyId())) {
//                throw new UnauthorizedException("해당 반려동물에 접근할 권한이 없습니다.");
//            }
//        }

        if (!pet.getUser().getUserId().equals(user.getUserId())) {
            throw new UnauthorizedException("해당 반려동물 정보를 수정할 권한이 없습니다.");
        }

        // 이미 'DELETE' 상태인지 확인
        if (pet.getPetState() == PetState.DELETE) {
            throw new AlreadyDeletedException("이미 삭제된 반려동물입니다.");
        }

        // jpa delete 호출
        jpaPetRepository.delete(pet);


    }
}
