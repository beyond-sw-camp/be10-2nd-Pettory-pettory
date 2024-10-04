package com.pettory.userserver.walkingRecord.query.service;

import com.pettory.userserver.exception.NotFoundException;
import com.pettory.userserver.exception.UnauthorizedException;
import com.pettory.userserver.pet.command.domain.aggregate.Pet;
import com.pettory.userserver.pet.command.domain.repository.PetRepository;
import com.pettory.userserver.security.util.UserSecurity;
import com.pettory.userserver.user.command.domain.repository.UserRepository;
import com.pettory.userserver.user.query.mapper.UserMapper;
import com.pettory.userserver.walkingRecord.query.dto.WalkingRecordDailyResponse;
import com.pettory.userserver.walkingRecord.query.dto.WalkingRecordDetailResponse;
import com.pettory.userserver.walkingRecord.query.dto.WalkingRecordSummaryResponse;
import com.pettory.userserver.walkingRecord.query.mapper.WalkingRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkingRecordQueryService {

    private final WalkingRecordMapper walkingRecordMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PetRepository petRepository;


    // 1. 산책 기록 월별 요약 조회
    @Transactional(readOnly = true)
    public List<WalkingRecordSummaryResponse> getWalkingRecordSummary(
            String userEmail, int year, int month, Long petId, String filterType
    ) {

        UserSecurity.validateCurrentUser(userEmail);

        // 로그인 회원 id 조회
        Long userId = userMapper.findUserIdByEmail(userEmail).getUserId();

        // familyId 조회
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

        List<WalkingRecordSummaryResponse> walkingRecords;

        switch (filterType) {
            case "self":
                // 현재 회원의 반려동물 산책 기록 요약 조회
                walkingRecords = walkingRecordMapper.findWalkingRecordsByUserIdForPetAndMonth(userId, petId, year, month);
                break;
            case "family":
                if (familyId == null) {
                    // 가족이 없으면 빈 리스트 반환
                    walkingRecords = new ArrayList<>();
                } else {
                    // 가족 구성원의 반려동물 산책 기록 요약 조회
                    walkingRecords = walkingRecordMapper.findWalkingRecordsByFamilyExcludeUserAndMonth(familyId, userId, petId, year, month);
                }
                break;
            case "all":
                if (familyId == null) {
                    // 가족이 없으면 자신의 반려동물 산책 기록만 조회
                    walkingRecords = walkingRecordMapper.findWalkingRecordsByUserIdForPetAndMonth(userId, petId, year, month);
                } else {
                    // 회원 포함 가족 전체의 반려동물 산책 기록 요약 조회
                    walkingRecords = walkingRecordMapper.findAllWalkingRecordsByFamilyAndPetAndMonth(familyId, petId, year, month);
                }
                break;
            default:
                throw new NotFoundException("잘못된 filterType 값입니다: " + filterType);
        }

        return walkingRecords;
    }

    // 2. 날짜별 산책 기록 조회
    @Transactional(readOnly = true)
    public List<WalkingRecordDailyResponse> getWalkingRecordsByDate(String userEmail, Long petId, LocalDate date) {

        UserSecurity.validateCurrentUser(userEmail);

        // 로그인 회원 id 조회
        Long userId = userMapper.findUserIdByEmail(userEmail).getUserId();

        // 반려동물 조회
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new NotFoundException("반려동물이 존재하지 않습니다."));

        // 가족 ID 조회
        Long familyId = userRepository.findFamilyIdByUserId(userId).orElse(null);

        // 해당 날짜의 모든 산책 기록 조회
        List<WalkingRecordDailyResponse> walkingRecords;

        if (familyId != null) {
            // 가족 구성원의 산책 기록까지 모두 조회
            walkingRecords = walkingRecordMapper.findWalkingRecordsByDateAndFamily(date, petId, familyId);
        } else {
            // 회원 본인의 산책 기록만 조회
            walkingRecords = walkingRecordMapper.findWalkingRecordsByDateAndUserId(date, petId, userId);
        }

        return walkingRecords;
    }

    // 3. 산책 기록 상세 조회(특정 산책 기록의 상세 정보 조회)
    @Transactional(readOnly = true)
    public WalkingRecordDetailResponse getWalkingRecordDetail(String userEmail, Long walkingRecordId) {

        UserSecurity.validateCurrentUser(userEmail);

        // 로그인 회원 id 조회
        Long userId = userMapper.findUserIdByEmail(userEmail).getUserId();

        // 가족 ID 조회
        Long familyId = userRepository.findFamilyIdByUserId(userId).orElse(null);

        WalkingRecordDetailResponse walkingRecordDetail;

        // familyId가 있으면 가족 내에서 조회, 없으면 현재 회원으로 조회
        if (familyId != null) {
            // 가족 구성원의 산책 기록까지 모두 조회
            walkingRecordDetail = walkingRecordMapper.findWalkingRecordDetailByIdAndFamily(walkingRecordId, familyId);
        } else {
            // 회원 본인의 산책 기록만 조회
            walkingRecordDetail = walkingRecordMapper.findWalkingRecordDetailByIdAndUserId(walkingRecordId, userId);
        }

        if (walkingRecordDetail == null) {
            throw new NotFoundException("해당 산책 기록을 찾을 수 없습니다.");
        }

        return walkingRecordDetail;
    }
}
