package org.project.domain.diary.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record EmotionRequest(
        @Schema(description = "감정 타입 (heart, good, tear, clap, fire)",
                example = "heart")
        String emotion
) {
    public static EmotionRequest of(String emotion) {
        return new EmotionRequest(emotion);
    }
}
