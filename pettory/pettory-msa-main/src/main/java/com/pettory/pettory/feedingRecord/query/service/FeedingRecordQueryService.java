package com.pettory.pettory.feedingRecord.query.service;

import com.pettory.pettory.exception.NotFoundException;
import com.pettory.pettory.exception.UnauthorizedException;
import com.pettory.pettory.feedingRecord.query.dto.FeedingRecordDailyResponse;
import com.pettory.pettory.feedingRecord.query.dto.FeedingRecordDetailResponse;
import com.pettory.pettory.feedingRecord.query.dto.FeedingRecordSummaryResponse;
import com.pettory.pettory.feedingRecord.query.mapper.FeedingRecordMapper;
import com.pettory.pettory.pet.command.domain.aggregate.Pet;
import com.pettory.pettory.pet.command.domain.repository.PetRepository;
import com.pettory.pettory.security.util.UserSecurity;
import com.pettory.pettory.user.command.domain.repository.UserRepository;
import com.pettory.pettory.user.query.mapper.UserMapper;
import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordDailyResponse;
import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedingRecordQueryService {

    private final FeedingRecordMapper feedingRecordMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    // 1. 급여 기록 월별 요약 조회
    public List<FeedingRecordSummaryResponse> getFeedingRecordSummary(
            String userEmail, int year, int month, Long petId
    ) {
        UserSecurity.validateCurrentUser(userEmail);

        // 로그인 회원 id 조회
        Long userId = userMapper.findUserIdByEmail(userEmail).getUserId();

        // 가족 id 조회
        // 회원이 속한 가족이 없으면 null 처리
        Long familyId = userRepository.findFamilyIdByUserId(userId).orElse(null);

        // 반려동물 정보 조회
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("반려동물이 존재하지 않습니다."));

        // 해당 반려동물을 현재 회원이 등록한 것이 아니라면
        if (!pet.getUser().getUserId().equals(userId)) {
            // 해당 반려동물을 가족 구성원이 등록한 것인지 확인
            if (familyId == null
                    || pet.getFamily() == null
                    || !familyId.equals(pet.getFamily().getFamilyId())) {
                throw new UnauthorizedException("해당 반려동물에 접근할 권한이 없습니다.");
            }
        }

        List<FeedingRecordSummaryResponse> feedingRecords;


        if (familyId == null) {
            // 1-1. 가족이 없으면 자신의 반려동물 급여 기록만 조회
            feedingRecords = feedingRecordMapper.findFeedingRecordsByUserIdForPetAndMonth(userId, petId, year, month);
        } else {
            // 1-2. 가족이 있으면 회원 포함 가족 전체의 반려동물 급여 기록 요약 조회
            feedingRecords = feedingRecordMapper.findAllFeedingRecordsByFamilyAndPetAndMonth(familyId, petId, year, month);
        }
        return feedingRecords;
    }


    // 2. 날짜별 산책 기록 조회
    public List<FeedingRecordDailyResponse> getFeedingRecordsByDate(
            String userEmail, Long petId, LocalDate date
    ) {
        UserSecurity.validateCurrentUser(userEmail);

        // 로그인 회원 id 조회
        Long userId = userMapper.findUserIdByEmail(userEmail).getUserId();

        // 가족 ID 조회
        Long familyId = userRepository.findFamilyIdByUserId(userId).orElse(null);

        // 해당 날짜의 모든 급여 기록 조회
        List<FeedingRecordDailyResponse> feedingRecords;

        // 반려동물 정보 조회
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("반려동물이 존재하지 않습니다."));

        // 해당 반려동물을 현재 회원이 등록한 것이 아니라면
        if (!pet.getUser().getUserId().equals(userId)) {
            // 해당 반려동물을 가족 구성원이 등록한 것인지 확인
            if (familyId == null
                    || pet.getFamily() == null
                    || !familyId.equals(pet.getFamily().getFamilyId())) {
                throw new UnauthorizedException("해당 반려동물에 접근할 권한이 없습니다.");
            }
        }

        if (familyId != null) {
            // 가족 구성원의 급여 기록까지 모두 조회
            feedingRecords = feedingRecordMapper.findFeedingRecordsByDateAndFamily(petId, date, familyId);
        } else {
            // 회원 본인의 급여 기록만 조회
            feedingRecords = feedingRecordMapper.findFeedingRecordsByDateAndUserId(petId, date, userId);
        }

        return feedingRecords;
    }

    // 3. 급여 기록 상세 조회
    public FeedingRecordDetailResponse getWalkingRecordDetail(String userEmail, Long feedingRecordId) {
        UserSecurity.validateCurrentUser(userEmail);

        // 로그인 회원 id 조회
        Long userId = userMapper.findUserIdByEmail(userEmail).getUserId();
        log.info("userId: " + userId);

        // 가족 ID 조회
        Long familyId = userRepository.findFamilyIdByUserId(userId).orElse(null);
        log.info("familyId: " + familyId);
        FeedingRecordDetailResponse feedingRecordDetail;

        // familyId가 있으면 가족 내에서 조회, 없으면 현재 회원으로 조회
        if (familyId != null) {
            // 가족 구성원의 산책 기록까지 모두 조회
            feedingRecordDetail = feedingRecordMapper.findFeedingRecordDetailByIdAndFamily(feedingRecordId, familyId);
        } else {
            // 회원 본인의 산책 기록만 조회
            feedingRecordDetail = feedingRecordMapper.findFeedingRecordDetailByIdAndUserId(feedingRecordId, userId);
        }

        if (feedingRecordDetail == null) {
            throw new NotFoundException("해당 산책 기록을 찾을 수 없습니다.");
        }

        return feedingRecordDetail;
    }
}
