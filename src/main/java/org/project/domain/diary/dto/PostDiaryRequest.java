package org.project.domain.diary.dto;

public record PostDiaryRequest(
        String subject,
        String subjectType,
        String title,
        String content
) {
}
