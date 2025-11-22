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
        int one,
        int two,
        int three,
        int four,
        int five
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
                diary.getOne(),
                diary.getTwo(),
                diary.getThree(),
                diary.getFour(),
                diary.getFive()
        );
    }
}
