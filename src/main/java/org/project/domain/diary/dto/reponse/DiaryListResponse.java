package org.project.domain.diary.dto.reponse;

import org.project.domain.diary.entity.Diary;

import java.util.List;

public record DiaryListResponse(
        List<DiaryResponse> diaries
) {

    public static DiaryListResponse from(List<Diary> diaryList) {
        List<DiaryResponse> responses = diaryList.stream()
                .map(DiaryResponse::from)
                .toList();

        return new DiaryListResponse(responses);
    }
}
