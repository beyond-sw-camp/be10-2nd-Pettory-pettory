package com.pettory.pettory.feedingRecord.command.application.service;

import com.pettory.pettory.exception.AlreadyDeletedException;
import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.exception.UnauthorizedException;
import com.pettory.pettory.feedingRecord.command.application.dto.FeedingRecordCreateRequest;
import com.pettory.pettory.feedingRecord.command.application.dto.FeedingRecordUpdateRequest;
import com.pettory.pettory.feedingRecord.command.domain.aggregate.FeedingRecord;
import com.pettory.pettory.feedingRecord.command.domain.aggregate.FeedingRecordState;
import com.pettory.pettory.feedingRecord.command.domain.repository.FeedingRecordRepository;
import com.pettory.pettory.feedingRecord.command.infrastructure.repository.JpaFeedingRecordRepository;
import com.pettory.pettory.feedingRecord.command.mapper.FeedingRecordMapper;
import com.pettory.pettory.pet.command.domain.aggregate.Pet;
import com.pettory.pettory.pet.command.domain.repository.PetRepository;
import com.pettory.pettory.security.util.UserSecurity;
import com.pettory.pettory.user.command.domain.aggregate.User;
import com.pettory.pettory.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedingRecordCommandService {

    private final FeedingRecordRepository feedingRecordRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final JpaFeedingRecordRepository jpaFeedingRecordRepository;

    // 새로운 급여 기록 등록
    @Transactional
    public Long addNewFeedingRecord(String userEmail, FeedingRecordCreateRequest feedingRecordCreateRequest) {

        UserSecurity.validateCurrentUser(userEmail);

        // 회원 정보 조회
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        // 반려동물 정보 조회
        Pet pet = petRepository.findById(feedingRecordCreateRequest.getPetId())
                .orElseThrow(() -> new NotFoundException("반려동물을 찾을 수 없습니다."));

        // 회원의 가족구성원이 등록한 반려동물인지 확인
        if (!pet.getUser().getUserId().equals(user.getUserId())) {
            if (user.getFamily() == null
                    || pet.getFamily() == null
                    || !user.getFamily().getFamilyId().equals(pet.getFamily().getFamilyId())) {
                throw new UnauthorizedException("반려동물을 등록할 권한이 없습니다.");
            }
        }

        FeedingRecord newFeedingRecord = FeedingRecordMapper.toEntity(user, pet, feedingRecordCreateRequest);

        FeedingRecord savedFeedingRecord = feedingRecordRepository.save(newFeedingRecord);

        return savedFeedingRecord.getFeedingRecordId();

    }

    // 급여 기록 수정
    @Transactional
    public void modifyWalkingRecord(String userEmail, Long feedingRecordId, FeedingRecordUpdateRequest feedingRecordUpdateRequest) {

        UserSecurity.validateCurrentUser(userEmail);

        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        // 수정하려는 급여 기록 조회
        FeedingRecord feedingRecord = feedingRecordRepository.findById(feedingRecordId)
                .orElseThrow(() -> new NotFoundException("급여 기록을 찾을 수 없습니다."));

        // 급여 기록을 현재 회원이 등록한 것이 아니라면 예외 발생
        if (!feedingRecord.getUser().getUserId().equals(user.getUserId())) {
            throw new UnauthorizedException("해당 급여 기록을 수정할 권한이 없습니다.");
        }

        // 급여 기록 수정
        feedingRecord.update(
                feedingRecordUpdateRequest.getFeedingRecordUserInsertDatetime(),
                feedingRecordUpdateRequest.getFeedingRecordFeedingType(),
                feedingRecordUpdateRequest.getFeedingRecordName(),
                feedingRecordUpdateRequest.getFeedingRecordAmount(),
                feedingRecordUpdateRequest.getFeedingRecordType(),
                feedingRecordUpdateRequest.getFeedingRecordMemo()
        );
    }

    // 급여 기록 삭제
    @Transactional
    public void deleteFeedingRecord(String userEmail, Long feedingRecordId) {
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("회원을 찾을 수 없습니다."));

        // 삭제하려는 급여 기록 조회
        FeedingRecord feedingRecord = feedingRecordRepository.findById(feedingRecordId)
                .orElseThrow(() -> new NotFoundException("산책 기록을 찾을 수 없습니다."));

        // 급여 기록을 현재 회원이 등록한 것이 아니라면 예외 발생
        if (!feedingRecord.getUser().getUserId().equals(user.getUserId())) {
            throw new UnauthorizedException("해당 급여 기록을 수정할 권한이 없습니다.");
        }

        // 이미 'DELETE' 상태인지 확인
        if (feedingRecord.getFeedingRecordState() == FeedingRecordState.DELETE) {
            throw new AlreadyDeletedException("이미 삭제된 급여 기록입니다.");
        }

        // 급여 기록 삭제
        jpaFeedingRecordRepository.delete(feedingRecord);
    }
}
