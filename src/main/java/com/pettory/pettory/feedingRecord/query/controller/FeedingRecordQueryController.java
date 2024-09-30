package com.pettory.pettory.feedingRecord.query.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.feedingRecord.query.dto.FeedingRecordDailyResponse;
import com.pettory.pettory.feedingRecord.query.dto.FeedingRecordDetailResponse;
import com.pettory.pettory.feedingRecord.query.dto.FeedingRecordSummaryResponse;
import com.pettory.pettory.feedingRecord.query.service.FeedingRecordQueryService;
import com.pettory.pettory.security.util.UserSecurity;
import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordDailyResponse;
import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordDetailResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("feeding-records")
public class FeedingRecordQueryController {

    private final FeedingRecordQueryService feedingRecordQueryService;
    private final UserSecurity userSecurity;

    // 1. 급여 기록 월별 요약 조회
    @GetMapping("/summary/{year}/{month}/{petId}")
    public ResponseEntity<CommonResponseDTO> getFeedingRecordSummary(
            @PathVariable("year") int year,
            @PathVariable("month") int month,
            @PathVariable("petId") Long petId
    ) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        List<FeedingRecordSummaryResponse> summaries = feedingRecordQueryService.getFeedingRecordSummary(currentUserEmail, year, month, petId);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "급여 기록 요약 조회 성공", summaries);
        return ResponseEntity.ok(successResponse);
    }

    // 2. 날짜별 급여 기록 조회
    @GetMapping("/{petId}/{year}/{month}/{day}")
    public ResponseEntity<CommonResponseDTO> getFeedingRecordsByDate(
            @PathVariable Long petId,
            @PathVariable int year,
            @PathVariable int month,
            @PathVariable int day
    ) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        LocalDate date = LocalDate.of(year, month, day);

        List<FeedingRecordDailyResponse> feedingRecords = feedingRecordQueryService.getFeedingRecordsByDate(currentUserEmail, petId, date);

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.OK.value(), "급여 기록 조회 성공", feedingRecords);
        return ResponseEntity.ok(commonResponseDTO);
    }

    // 3. 급여 기록 상세 조회
    @GetMapping("/detail/{feedingRecordId}")
    public ResponseEntity<CommonResponseDTO> getFeedingRecordDetail(@PathVariable Long feedingRecordId) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();
        FeedingRecordDetailResponse feedingRecordDetailResponse = feedingRecordQueryService.getWalkingRecordDetail(currentUserEmail, feedingRecordId);

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.OK.value(), "급여 기록 상세 조회 성공", feedingRecordDetailResponse);
        return ResponseEntity.ok(commonResponseDTO);
    }
}
