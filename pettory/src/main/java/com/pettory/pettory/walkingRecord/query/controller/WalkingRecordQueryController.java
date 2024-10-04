package com.pettory.pettory.walkingRecord.query.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.security.util.UserSecurity;
import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordDailyResponse;
import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordDetailResponse;
import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordSummaryResponse;
import com.pettory.pettory.walkingRecord.query.service.WalkingRecordQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Pettory 산책 기록 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("walking-records")
public class WalkingRecordQueryController {

    private final WalkingRecordQueryService walkingRecordQueryService;

    @Operation(summary = "산책 기록 월별 요약", description = "월별로 산책 기록을 요약해서 조회한다.")
    // 1-1. 산책 기록 월별 요약 조회
    @GetMapping("/summary/{year}/{month}/{petId}")
    public ResponseEntity<CommonResponseDTO> getWalkingRecordSummary(
            @PathVariable("year") @Schema(example = "2024") int year,
            @PathVariable("month") @Schema(example = "10") int month,
            @PathVariable("petId") @Schema(example = "02") Long petId,
            @RequestParam(value = "filterType", required = false, defaultValue = "all") @Schema(example = "all") String filterType
    ) {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        List<WalkingRecordSummaryResponse> summaries = walkingRecordQueryService.getWalkingRecordSummary(currentUserEmail, year, month, petId, filterType);

        CommonResponseDTO response = new CommonResponseDTO(HttpStatus.OK.value(), "산책 기록 요약 조회 성공", summaries);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "산책 기록 날짜별 요약 조회", description = "날짜별로 산책 기록을 요약 조회한다.")
    // 2. 날짜별 산책 기록 조회
    @GetMapping("/{date}/{petId}")
    public ResponseEntity<CommonResponseDTO> getWalkingRecordsByDate(
            @PathVariable @Schema(example = "2024-10-02") LocalDate date,
            @PathVariable @Schema(example = "7") Long petId
    ) {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        List<WalkingRecordDailyResponse> walkingRecords = walkingRecordQueryService.getWalkingRecordsByDate(currentUserEmail, petId, date);

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.OK.value(), "산책 기록 조회 성공", walkingRecords);
        return ResponseEntity.ok(commonResponseDTO);
    }

    @Operation(summary = "산책 기록 특정 날짜", description = "특정한 날짜의 산책 기록을 상세 조회한다.")
    // 3. 산책 기록 상세 조회(특정 산책 기록의 상세 정보 조회)
    @GetMapping("/detail/{walkingRecordId}")
    public ResponseEntity<CommonResponseDTO> getWalkingRecordDetail(@PathVariable @Schema(example = "10") Long walkingRecordId) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();
        WalkingRecordDetailResponse walkingRecordDetailResponse = walkingRecordQueryService.getWalkingRecordDetail(currentUserEmail, walkingRecordId);

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.OK.value(), "산책 기록 상세 조회 성공", walkingRecordDetailResponse);
        return ResponseEntity.ok(commonResponseDTO);
    }

}
