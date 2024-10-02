package com.pettory.pettory.walkingRecord.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.security.util.UserSecurity;
import com.pettory.pettory.walkingRecord.command.application.dto.WalkingRecordCreateRequest;
import com.pettory.pettory.walkingRecord.command.application.dto.WalkingRecordUpdateRequest;
import com.pettory.pettory.walkingRecord.command.application.service.WalkingRecordCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/walking-records")
public class WalkingRecordCommandController {

    private final WalkingRecordCommandService walkingRecordCommandService;

    // 새 산책 기록 등록
    @PostMapping()
    public ResponseEntity<CommonResponseDTO> insertNewWalkingRecord(@RequestBody WalkingRecordCreateRequest walkingRecordCreateRequest) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        Long newWalkingRecordId = walkingRecordCommandService.addNewWalkingRecord(currentUserEmail, walkingRecordCreateRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "새 산책 기록 등록 성공", newWalkingRecordId);
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    // 산책 기록 수정
    @PutMapping("/{walkingRecordId}")
    public ResponseEntity<CommonResponseDTO> modifyWalkingRecord(
            @PathVariable Long walkingRecordId,
            @RequestBody WalkingRecordUpdateRequest walkingRecordUpdateRequest
            ) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        walkingRecordCommandService.modifyWalkingRecord(currentUserEmail, walkingRecordId, walkingRecordUpdateRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "산책 기록 수정 성공", null);
        return ResponseEntity.ok(successResponse);
    }

    // 산책 기록 삭제
    @DeleteMapping("/{walkingRecordId}")
    public ResponseEntity<CommonResponseDTO> deleteWalkingRecord(@PathVariable Long walkingRecordId) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        walkingRecordCommandService.deleteWalkingRecord(currentUserEmail, walkingRecordId);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "산책 기록 삭제 성공", null);
        return ResponseEntity.ok(successResponse);
    }

}
