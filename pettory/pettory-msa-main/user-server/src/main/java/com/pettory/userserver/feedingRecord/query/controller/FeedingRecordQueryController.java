package com.pettory.userserver.feedingRecord.query.controller;

import com.pettory.userserver.common.CommonResponseDTO;
import com.pettory.userserver.feedingRecord.query.dto.FeedingRecordDailyResponse;
import com.pettory.userserver.feedingRecord.query.dto.FeedingRecordDetailResponse;
import com.pettory.userserver.feedingRecord.query.dto.FeedingRecordSummaryResponse;
import com.pettory.userserver.feedingRecord.query.service.FeedingRecordQueryService;
import com.pettory.userserver.security.util.UserSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
@Tag(name = "Pettory 급여 기록 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("feeding-records")
public class FeedingRecordQueryController {

    private final FeedingRecordQueryService feedingRecordQueryService;
    private final UserSecurity userSecurity;

    @Operation(summary = "급여 기록 월별 요약 조회", description = "월별로 급여 기록을 요약해서 조회한다.")
    // 1. 급여 기록 월별 요약 조회
    @GetMapping("/summary/{year}/{month}/{petId}")
    public ResponseEntity<CommonResponseDTO> getFeedingRecordSummary(
            @PathVariable("year") @Schema(example = "2024") int year,
            @PathVariable("month") @Schema(example = "10") int month,
            @PathVariable("petId") @Schema(example = "7") Long petId
    ) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        List<FeedingRecordSummaryResponse> summaries = feedingRecordQueryService.getFeedingRecordSummary(currentUserEmail, year, month, petId);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "급여 기록 요약 조회 성공", summaries);
        return ResponseEntity.ok(successResponse);
    }

    @Operation(summary = "급여 기록 날짜별 조회", description = "날짜별로 급여 기록을 요약 조회한다.")
    // 2. 날짜별 급여 기록 조회
    @GetMapping("/{petId}/{year}/{month}/{day}")
    public ResponseEntity<CommonResponseDTO> getFeedingRecordsByDate(
            @PathVariable @Schema(example = "7") Long petId,
            @PathVariable @Schema(example = "2024") int year,
            @PathVariable @Schema(example = "10") int month,
            @PathVariable @Schema(example = "02") int day
    ) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        LocalDate date = LocalDate.of(year, month, day);

        List<FeedingRecordDailyResponse> feedingRecords = feedingRecordQueryService.getFeedingRecordsByDate(currentUserEmail, petId, date);

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.OK.value(), "급여 기록 조회 성공", feedingRecords);
        return ResponseEntity.ok(commonResponseDTO);
    }

    @Operation(summary = "급여 기록 특정 날짜 조회", description = "특정한 날짜의 급여 기록을 상세 조회한다.")
    // 3. 급여 기록 상세 조회
    @GetMapping("/detail/{feedingRecordId}")
    public ResponseEntity<CommonResponseDTO> getFeedingRecordDetail(@PathVariable @Schema(example = "7") Long feedingRecordId) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();
        FeedingRecordDetailResponse feedingRecordDetailResponse = feedingRecordQueryService.getWalkingRecordDetail(currentUserEmail, feedingRecordId);

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.OK.value(), "급여 기록 상세 조회 성공", feedingRecordDetailResponse);
        return ResponseEntity.ok(commonResponseDTO);
    }
}
