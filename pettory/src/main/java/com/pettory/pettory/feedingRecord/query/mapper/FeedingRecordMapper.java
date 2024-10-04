package com.pettory.pettory.feedingRecord.query.mapper;

import com.pettory.pettory.feedingRecord.query.dto.FeedingRecordDailyResponse;
import com.pettory.pettory.feedingRecord.query.dto.FeedingRecordDetailResponse;
import com.pettory.pettory.feedingRecord.query.dto.FeedingRecordSummaryResponse;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface FeedingRecordMapper {

    // 1-1. 급여 기록 월별 요약 조회 - 가족이 없는 경우 자신의 반려동물 급여 기록만 조회
    List<FeedingRecordSummaryResponse> findFeedingRecordsByUserIdForPetAndMonth(Long userId, Long petId, int year, int month);

    // 1-2. 급여 기록 월별 요약 조회 - 가족이 있으면 회원 포함 가족 전체의 반려동물 급여 기록 요약 조회
    List<FeedingRecordSummaryResponse> findAllFeedingRecordsByFamilyAndPetAndMonth(Long familyId, Long petId, int year, int month);

    // 2-1. 날짜별 산책 기록 조회 - 가족 구성원의 급여 기록까지 모두 조회
    List<FeedingRecordDailyResponse> findFeedingRecordsByDateAndFamily(Long petId, LocalDate date, Long familyId);

    // 2-2. 날짜별 산책 기록 조회 - 회원 본인의 급여 기록만 조회
    List<FeedingRecordDailyResponse> findFeedingRecordsByDateAndUserId(Long petId, LocalDate date, Long userId);

    // 3-1. 급여 기록 상세 조회 - 가족이 있으면 회원 포함 가족 전체의 산책 기록 모두 조회
    FeedingRecordDetailResponse findFeedingRecordDetailByIdAndFamily(Long feedingRecordId, Long familyId);
    // 3-2. 급여 기록 상세 조회 - 회원 본인의 산책 기록만 조회
    FeedingRecordDetailResponse findFeedingRecordDetailByIdAndUserId(Long feedingRecordId, Long userId);
}
