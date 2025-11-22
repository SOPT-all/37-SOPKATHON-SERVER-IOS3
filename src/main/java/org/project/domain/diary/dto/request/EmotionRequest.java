package org.project.domain.diary.dto.request;

public record EmotionRequest(
        String emotion
) {
    public static EmotionRequest of(String emotion) {
        return new EmotionRequest(emotion);
    }
}
