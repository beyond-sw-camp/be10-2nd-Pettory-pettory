package com.pettory.pettory.feedingRecord.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.feedingRecord.command.application.dto.FeedingRecordCreateRequest;
import com.pettory.pettory.feedingRecord.command.application.dto.FeedingRecordUpdateRequest;
import com.pettory.pettory.feedingRecord.command.application.service.FeedingRecordCommandService;
import com.pettory.pettory.security.util.UserSecurity;
import com.pettory.pettory.walkingRecord.command.application.dto.WalkingRecordUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feeding-records")
public class FeedingRecordCommandController {

    private final FeedingRecordCommandService feedingRecordCommandService;

    // 새 급여 기록 등록
    @PostMapping
    public ResponseEntity<CommonResponseDTO> insertNewFeedingRecord(@RequestBody FeedingRecordCreateRequest feedingRecordCreateRequest) {

        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        Long newFeedingRecordId = feedingRecordCommandService.addNewFeedingRecord(currentUserEmail, feedingRecordCreateRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "새 급여 기록 등록 성공", newFeedingRecordId);
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    // 급여 기록 수정
    @PutMapping("/{feedingRecordId}")
    public ResponseEntity<CommonResponseDTO> modifyWalkingRecord(
            @PathVariable Long feedingRecordId,
            @RequestBody FeedingRecordUpdateRequest feedingRecordUpdateRequest
    ) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        feedingRecordCommandService.modifyWalkingRecord(currentUserEmail, feedingRecordId, feedingRecordUpdateRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "급여 기록 수정 성공", null);
        return ResponseEntity.ok(successResponse);
    }

    // 급여 기록 삭제
    // delte 시 삭제 시간 update 해야함
    @DeleteMapping("/{feedingRecordId}")
    public ResponseEntity<CommonResponseDTO> deleteFeedinggRecord(@PathVariable Long feedingRecordId) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        feedingRecordCommandService.deleteFeedingRecord(currentUserEmail, feedingRecordId);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "급여 기록 삭제 성공", null);
        return ResponseEntity.ok(successResponse);
    }
}
