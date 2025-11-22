package org.project.domain.diary.dto.reponse;

import org.project.domain.diary.entity.Diary;

public record RandomDiaryResponse(
        Long id,
        String title,
        String content
) {

    // 정적 팩토리 메서드
    public static RandomDiaryResponse from(Diary diary) {
        return new RandomDiaryResponse(
                diary.getId(),
                diary.getTitle(),
                diary.getContent()
        );
    }
}
