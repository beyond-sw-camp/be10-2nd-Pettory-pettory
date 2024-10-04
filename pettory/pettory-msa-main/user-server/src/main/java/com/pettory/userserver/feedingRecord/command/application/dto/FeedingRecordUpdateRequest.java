package com.pettory.userserver.feedingRecord.command.application.dto;

import com.pettory.userserver.feedingRecord.command.domain.aggregate.FeedingRecordFeedingType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class FeedingRecordUpdateRequest {

    @Schema(example = "7")
    @NotNull
    private Long petId;

    @Schema(example = "2024-10-02T15:02:11")
    private LocalDateTime feedingRecordUserInsertDatetime;

    @Schema(example = "FEED")
    @NotNull
    private FeedingRecordFeedingType feedingRecordFeedingType;

    @Schema(example = "null")
    @NotNull
    private String feedingRecordName;

    @Schema(example = "건식 키블")
    @NotNull
    private String feedingRecordType;

    @Schema(example = "null")
    private String feedingRecordAmount;

    @Schema(example = "기호성 최악!! 다시는 안 사 ㅠ")
    private String feedingRecordMemo;
}
