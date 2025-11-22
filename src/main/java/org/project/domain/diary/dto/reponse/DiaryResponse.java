package org.project.domain.diary.dto.reponse;

import org.project.domain.diary.entity.Diary;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public record DiaryResponse(
        Long id,
        String title,
        String content,
        String createdAt,
        List<String> tags,
        int heart,
        int good,
        int tear,
        int clap,
        int fire
) {

    public static DiaryResponse from(Diary diary) {

        // createdAt 포맷팅
        String createdAtFormatted = diary.getCreatedAt()
                .format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));

        // tagList → ["a", "b", "c"]
        List<String> tags = diary.getTagList() == null || diary.getTagList().isBlank()
                ? List.of()
                : Arrays.asList(diary.getTagList().split(","));

        return new DiaryResponse(
                diary.getId(),
                diary.getTitle(),
                diary.getContent(),
                createdAtFormatted,
                tags,
                diary.getHeart(),
                diary.getGood(),
                diary.getTear(),
                diary.getClap(),
                diary.getFire()
        );
    }
}
