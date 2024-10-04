package com.pettory.pettory.walkingRecord.query.mapper;

import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordDailyResponse;
import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordDetailResponse;
import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordSummaryResponse;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface WalkingRecordMapper {


    // 1-1. 현재 회원의 반려동물 산책 기록 조회
    List<WalkingRecordSummaryResponse> findWalkingRecordsByUserIdForPetAndMonth(Long userId, Long petId, int year, int month);

    // 1-2. 가족 구성원 중 현재 회원을 제외한 다른 구성원들이 해당 반려동물에 대해 기록한 산책 기록 조회
    List<WalkingRecordSummaryResponse> findWalkingRecordsByFamilyExcludeUserAndMonth(Long familyId, Long userId, Long petId, int year, int month);

    // 1-3. 가족 전체의 해당 반려동물에 대한 모든 산책 기록 조회
    List<WalkingRecordSummaryResponse> findAllWalkingRecordsByFamilyAndPetAndMonth(Long familyId, Long petId, int year, int month);

    // 2. 날짜별 산책 기록 조회
    List<WalkingRecordDailyResponse> findWalkingRecordsByDateAndFamily(LocalDate date, Long petId, Long familyId);

    List<WalkingRecordDailyResponse> findWalkingRecordsByDateAndUserId(LocalDate date, Long petId, Long userId);
    
    // 3. 산책 기록 상세 조회(특정 산책 기록의 상세 정보 조회)
    WalkingRecordDetailResponse findWalkingRecordDetailByIdAndFamily(Long walkingRecordId, Long familyId);

    WalkingRecordDetailResponse findWalkingRecordDetailByIdAndUserId(Long walkingRecordId, Long userId);
}
