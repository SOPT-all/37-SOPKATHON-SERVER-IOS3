package org.project.domain.diary.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record PostDiaryRequest(
        @Schema(description = "일기의 주제(제목과는 별도 의미)", example = "밤하늘 속 작은 약속")
        String subject,

        @Schema(description = "주제 타입 (예: DAILY, PROMPT 등)", example = "PROMPT")
        String subjectType,

        @Schema(description = "일기의 제목", example = "오늘 엘프가 나에게 건넨 말")
        String title,

        @Schema(description = "일기 본문 내용", example = "오늘은 이상하게 마음이 포근했다. 길을 걷는데 마치 누군가가 '괜찮아'라고 속삭이는 것 같았다...")
        String content,

        @Schema(description = "작성한 사용자 ID", example = "3")
        Long userId
) {
}
