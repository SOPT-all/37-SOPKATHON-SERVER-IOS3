package org.project.domain.diary.dto;

public record PostDiaryRequest(
        Long userId,
        String subject,
        String subjectType,
        String title,
        String content
) {
}
