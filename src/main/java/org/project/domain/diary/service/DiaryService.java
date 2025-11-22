package org.project.domain.diary.service;

import lombok.RequiredArgsConstructor;
import org.project.domain.diary.dto.PostDiaryRequest;
import org.project.domain.diary.entity.Diary;
import org.project.domain.diary.repository.DiaryRepository;
import org.project.global.google.GoogleApiComponent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final GoogleApiComponent googleApiComponent;

    public void postDiary(PostDiaryRequest request) {

        Diary diary = new Diary(
                request.subject(),
                request.subjectType(),
                request.title(),
                request.content(),
                googleApiComponent.classifyTag(request.content())
        );

        diaryRepository.save(diary);
    }
}
