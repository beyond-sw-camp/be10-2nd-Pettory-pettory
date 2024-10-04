package com.pettory.userserver.feedingRecord.command.application.controller;

import com.pettory.userserver.common.CommonResponseDTO;
import com.pettory.userserver.feedingRecord.command.application.dto.FeedingRecordCreateRequest;
import com.pettory.userserver.feedingRecord.command.application.dto.FeedingRecordUpdateRequest;
import com.pettory.userserver.feedingRecord.command.application.service.FeedingRecordCommandService;
import com.pettory.userserver.security.util.UserSecurity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pettory 급여 기록 컨트롤러", description = "급여 기록 조회/등록/수정/삭제")
@RestController
@RequiredArgsConstructor
@RequestMapping("/feeding-records")
public class FeedingRecordCommandController {

    private final FeedingRecordCommandService feedingRecordCommandService;

    @Operation(summary = "급여 기록 등록", description = "새로운 급여 기록을 등록한다.")
    // 새 급여 기록 등록
    @PostMapping
    public ResponseEntity<CommonResponseDTO> insertNewFeedingRecord(@RequestBody FeedingRecordCreateRequest feedingRecordCreateRequest) {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        Long newFeedingRecordId = feedingRecordCommandService.addNewFeedingRecord(currentUserEmail, feedingRecordCreateRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "새 급여 기록 등록 성공", newFeedingRecordId);
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @Operation(summary = "급여 기록 수정", description = "기존의 급여 기록을 수정한다.")
    // 급여 기록 수정
    @PutMapping("/{feedingRecordId}")
    public ResponseEntity<CommonResponseDTO> modifyWalkingRecord(
            @PathVariable @Schema(example = "7") Long feedingRecordId,
            @RequestBody FeedingRecordUpdateRequest feedingRecordUpdateRequest
    ) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        feedingRecordCommandService.modifyWalkingRecord(currentUserEmail, feedingRecordId, feedingRecordUpdateRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "급여 기록 수정 성공", null);
        return ResponseEntity.ok(successResponse);
    }

    // 급여 기록 삭제
    // delte 시 삭제 시간 update 해야함
    @Operation(summary = "급여 기록 삭제", description = "기존의 급여 기록을 삭제한다.")
    @DeleteMapping("/{feedingRecordId}")
    public ResponseEntity<CommonResponseDTO> deleteFeedingRecord(@PathVariable @Schema(example = "8") Long feedingRecordId) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        feedingRecordCommandService.deleteFeedingRecord(currentUserEmail, feedingRecordId);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "급여 기록 삭제 성공", null);
        return ResponseEntity.ok(successResponse);
    }
}
