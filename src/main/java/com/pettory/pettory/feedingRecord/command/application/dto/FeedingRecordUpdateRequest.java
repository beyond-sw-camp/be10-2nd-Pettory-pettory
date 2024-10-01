package com.pettory.pettory.feedingRecord.command.application.dto;

import com.pettory.pettory.feedingRecord.command.domain.aggregate.FeedingRecordFeedingType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class FeedingRecordUpdateRequest {
    @NotNull
    private Long petId;
    private LocalDateTime feedingRecordUserInsertDatetime;
    @NotNull
    private FeedingRecordFeedingType feedingRecordFeedingType;
    @NotNull
    private String feedingRecordName;
    @NotNull
    private String feedingRecordType;
    private String feedingRecordAmount;
    private String feedingRecordMemo;
}
