package com.pettory.pettory.walkingRecord.command.application.controller;

import com.pettory.pettory.common.CommonResponseDTO;
import com.pettory.pettory.security.util.UserSecurity;
import com.pettory.pettory.walkingRecord.command.application.dto.WalkingRecordCreateRequest;
import com.pettory.pettory.walkingRecord.command.application.dto.WalkingRecordUpdateRequest;
import com.pettory.pettory.walkingRecord.command.application.service.WalkingRecordCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pettory 산책 기록 컨트롤러", description = "산책 기록 등록/조회/수정/삭제")
@RestController
@RequiredArgsConstructor
@RequestMapping("/walking-records")
public class WalkingRecordCommandController {

    private final WalkingRecordCommandService walkingRecordCommandService;

    @Operation(summary = "산책 기록 등록", description = "새 산책 기록을 등록한다.")
    // 새 산책 기록 등록
    @PostMapping()
    public ResponseEntity<CommonResponseDTO> insertNewWalkingRecord(@RequestBody WalkingRecordCreateRequest walkingRecordCreateRequest) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        Long newWalkingRecordId = walkingRecordCommandService.addNewWalkingRecord(currentUserEmail, walkingRecordCreateRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.CREATED.value(), "새 산책 기록 등록 성공", newWalkingRecordId);
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @Operation(summary = "산책 기록 수정", description = "기존의 산책 기록을 수정한다.")
    // 산책 기록 수정
    @PutMapping("/{walkingRecordId}")
    public ResponseEntity<CommonResponseDTO> modifyWalkingRecord(
            @PathVariable @Schema(example = "10") Long walkingRecordId,
            @RequestBody WalkingRecordUpdateRequest walkingRecordUpdateRequest
            ) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        walkingRecordCommandService.modifyWalkingRecord(currentUserEmail, walkingRecordId, walkingRecordUpdateRequest);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "산책 기록 수정 성공", null);
        return ResponseEntity.ok(successResponse);
    }

    @Operation(summary = "산책 기록 삭제", description = "기존의 산책 기록을 삭제한다.")
    // 산책 기록 삭제
    @DeleteMapping("/{walkingRecordId}")
    public ResponseEntity<CommonResponseDTO> deleteWalkingRecord(@PathVariable @Schema(example = "10") Long walkingRecordId) {
        String currentUserEmail = UserSecurity.getCurrentUserEmail();

        walkingRecordCommandService.deleteWalkingRecord(currentUserEmail, walkingRecordId);

        CommonResponseDTO successResponse = new CommonResponseDTO(HttpStatus.OK.value(), "산책 기록 삭제 성공", null);
        return ResponseEntity.ok(successResponse);
    }

}
