package com.pettory.userserver.feedingRecord.query.dto;

import com.pettory.userserver.feedingRecord.command.domain.aggregate.FeedingRecordFeedingType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FeedingRecordDailyResponse {
    private Long feedingRecordId; // 급여 기록 id
    private LocalDateTime feedingRecordUserInsertDatetime;  // 사용자가 입력한 급여 시간
    private FeedingRecordFeedingType feedingRecordFeedingType;  // 급여 종류(사료, 간식)
    private String userNickname;    // 급여 기록을 작성한 사람의 Nickname
}
