package com.pettory.userserver.feedingRecord.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedingRecordSummaryResponse {
    private LocalDateTime feedingRecordUserInsertDatetime;    // 급여 기록 등록 일시
    private Long feedingRecordFeedCount;    // 급여 기록 사료 기록 개수
    private Long feedingRecordSnackCount;   // 급여 기록 간식 기록 개수
}
