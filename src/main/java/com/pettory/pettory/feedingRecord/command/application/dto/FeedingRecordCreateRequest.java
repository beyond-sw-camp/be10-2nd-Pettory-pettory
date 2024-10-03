package com.pettory.pettory.feedingRecord.command.application.dto;

import com.pettory.pettory.feedingRecord.command.domain.aggregate.FeedingRecordFeedingType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class FeedingRecordCreateRequest {
    @Schema(example = "7")
    @NotNull
    private Long petId;

    @Schema(example = "2024-10-02T09:14:02")
    private LocalDateTime feedingRecordUserInsertDatetime;

    @Schema(example = "FEED")
    @NotNull
    private FeedingRecordFeedingType feedingRecordFeedingType;

    @Schema(example = "나우 독 시니어 치킨 2kg")
    @NotNull
    private String feedingRecordName;

    @Schema(example = "건식")
    @NotNull
    private String feedingRecordType;

    @Schema(example = "150g")
    private String feedingRecordAmount;

    @Schema(example = "기호성 bad")
    private String feedingRecordMemo;
}
