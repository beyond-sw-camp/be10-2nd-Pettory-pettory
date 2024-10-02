package com.pettory.pettory.walkingRecord.query.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.security.util.UserSecurity;
import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordDailyResponse;
import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordDetailResponse;
import com.pettory.pettory.walkingRecord.query.dto.WalkingRecordSummaryResponse;
import com.pettory.pettory.walkingRecord.query.service.WalkingRecordQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("walking-records")
public class WalkingRecordQueryController {

    private final WalkingRecordQueryService walkingRecordQueryService;

    // 1-1. 산책 기록 월별 요약 조회
    @GetMapping("/summary/{year}/{month}/{petId}")
    public ResponseEntity<CommonResponseDTO> getWalkingRecordSummary(
            @PathVariable("year") int year,
            @PathVariable("month") int month,
            @PathVariable("petId") Long petId,
            @RequestParam(value = "filterType", required = false, defaultValue = "all") String filterType

    ) {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        List<WalkingRecordSummaryResponse> summaries = walkingRecordQueryService.getWalkingRecordSummary(currentUserEmail, year, month, petId, filterType);

        CommonResponseDTO response = new CommonResponseDTO(HttpStatus.OK.value(), "산책 기록 요약 조회 성공", summaries);
        return ResponseEntity.ok(response);
    }

    // 2. 날짜별 산책 기록 조회
    @GetMapping("/{date}/{petId}")
    public ResponseEntity<CommonResponseDTO> getWalkingRecordsByDate(
            @PathVariable LocalDate date,
            @PathVariable Long petId
    ) {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        List<WalkingRecordDailyResponse> walkingRecords = walkingRecordQueryService.getWalkingRecordsByDate(currentUserEmail, petId, date);

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.OK.value(), "산책 기록 조회 성공", walkingRecords);
        return ResponseEntity.ok(commonResponseDTO);
    }


    // 3. 산책 기록 상세 조회(특정 산책 기록의 상세 정보 조회)
    @GetMapping("/detail/{walkingRecordId}")
    public ResponseEntity<CommonResponseDTO> getWalkingRecordDetail(@PathVariable Long walkingRecordId) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();
        WalkingRecordDetailResponse walkingRecordDetailResponse = walkingRecordQueryService.getWalkingRecordDetail(currentUserEmail, walkingRecordId);

        CommonResponseDTO commonResponseDTO = new CommonResponseDTO(HttpStatus.OK.value(), "산책 기록 상세 조회 성공", walkingRecordDetailResponse);
        return ResponseEntity.ok(commonResponseDTO);
    }

}
