package com.pettory.pettory.walkingRecord.command.application.service;

import com.pettory.pettory.exception.AlreadyDeletedException;
import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.exception.UnauthorizedException;
import com.pettory.pettory.feedingRecord.command.domain.aggregate.FeedingRecordState;
import com.pettory.pettory.pet.command.domain.aggregate.Pet;
import com.pettory.pettory.pet.command.domain.repository.PetRepository;
import com.pettory.pettory.security.util.UserSecurity;
import com.pettory.pettory.user.command.domain.aggregate.User;
import com.pettory.pettory.user.command.domain.repository.UserRepository;
import com.pettory.pettory.walkingRecord.command.application.dto.WalkingRecordCreateRequest;
import com.pettory.pettory.walkingRecord.command.application.dto.WalkingRecordUpdateRequest;
import com.pettory.pettory.walkingRecord.command.domain.aggregate.WalkingRecord;
import com.pettory.pettory.walkingRecord.command.domain.aggregate.WalkingRecordState;
import com.pettory.pettory.walkingRecord.command.domain.repository.WalkingRecordRepository;
import com.pettory.pettory.walkingRecord.command.infrastructure.repository.JpaWalkingRecordRepository;
import com.pettory.pettory.walkingRecord.command.mapper.WalkingRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalkingRecordCommandService {

    private final WalkingRecordRepository walkingRecordRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final JpaWalkingRecordRepository jpaWalkingRecordRepository;

    // 새로운 산책 기록 등록
    @Transactional
    public Long addNewWalkingRecord(String userEmail, WalkingRecordCreateRequest walkingRecordCreateRequest) {

        UserSecurity.validateCurrentUser(userEmail);

        // 회원 정보 조회
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        // 반려동물 정보 조회
        Pet pet = petRepository.findById(walkingRecordCreateRequest.getPetId())
                .orElseThrow(() -> new NotFoundException("반려동물을 찾을 수 없습니다."));

        // 회원의 가족구성원이 등록한 반려동물인지 확인
        if (!pet.getUser().getUserId().equals(user.getUserId())) {
            if (user.getFamily() == null
                    || pet.getFamily() == null
                    || !user.getFamily().getFamilyId().equals(pet.getFamily().getFamilyId())) {
                throw new UnauthorizedException("반려동물을 등록할 권한이 없습니다.");
            }
        }

        WalkingRecord newWalkingRecord = WalkingRecordMapper.toEntity(user, pet, walkingRecordCreateRequest);

        WalkingRecord savedWalkingRecord = walkingRecordRepository.save(newWalkingRecord);

        return savedWalkingRecord.getWalkingRecordId();
    }

    // 산책 기록 수정
    @Transactional
    public void modifyWalkingRecord(String userEmail, Long walkingRecordId, WalkingRecordUpdateRequest walkingRecordUpdateRequest) {

        UserSecurity.validateCurrentUser(userEmail);

        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        // 수정하려는 산책 기록 조회
        WalkingRecord walkingRecord = walkingRecordRepository.findById(walkingRecordId)
                .orElseThrow(() -> new NotFoundException("산책 기록을 찾을 수 없습니다."));

        // 산책 기록을 현재 회원이 등록한 것이 아니라면 예외 발생
        if (!walkingRecord.getUser().getUserId().equals(user.getUserId())) {
            throw new UnauthorizedException("해당 급여 기록을 수정할 권한이 없습니다.");
        }

        // 산책 기록 수정
        walkingRecord.update(
                walkingRecordUpdateRequest.getWalkingRecordDate(),
                walkingRecordUpdateRequest.getWalkingRecordDuration(),
                walkingRecordUpdateRequest.getWalkingRecordPoopCount(),
                walkingRecordUpdateRequest.getWalkingRecordWaterAmount(),
                walkingRecordUpdateRequest.getWalkingRecordMemo()
        );
    }

    // 산책 기록 삭제
    @Transactional
    public void deleteWalkingRecord(String userEmail, Long walkingRecordId) {
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        // 삭제하려는 산책 기록 조회
        WalkingRecord walkingRecord = walkingRecordRepository.findById(walkingRecordId)
                .orElseThrow(() -> new NotFoundException("산책 기록을 찾을 수 없습니다."));

        // 산책 기록을 현재 회원이 등록한 것이 아니라면
        if (!walkingRecord.getUser().getUserId().equals(user.getUserId())) {
            throw new UnauthorizedException("해당 산책 기록을 수정할 권한이 없습니다.");
        }

        // 이미 'DELETE' 상태인지 확인
        if (walkingRecord.getWalkingRecordState() == WalkingRecordState.DELETE) {
            throw new AlreadyDeletedException("이미 삭제된 급여 기록입니다.");
        }

        // 산책 기록 삭제
        jpaWalkingRecordRepository.delete(walkingRecord);
    }
}
