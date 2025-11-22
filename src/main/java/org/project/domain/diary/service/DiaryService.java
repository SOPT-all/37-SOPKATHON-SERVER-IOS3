package org.project.domain.diary.service;

import lombok.RequiredArgsConstructor;
import org.project.domain.diary.dto.PostDiaryRequest;
import org.project.global.google.GoogleApiComponent;
import org.project.domain.diary.dto.reponse.DiaryListResponse;
import org.project.domain.diary.dto.reponse.RandomDiaryResponse;
import org.project.domain.diary.entity.Diary;
import org.project.domain.diary.repository.DiaryRepository;
import org.project.global.api.ErrorCode;
import org.project.global.api.GeneralException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryService {

    private final Random random = new Random();

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

    public RandomDiaryResponse getRandomDiary() {

        long count = diaryRepository.count();

        if (count == 0) {
            throw new IllegalStateException("등록된 일기가 없습니다.");
        }

        // 0 ~ count-1 중 랜덤 인덱스 선택
        int index = random.nextInt((int) count);

        Optional<Diary> randomDiary = diaryRepository.findRandomDiary(index);

        Diary diary = randomDiary
                .orElseThrow(() -> new GeneralException(ErrorCode.FAIL_RANDOM_DIARY));

        return RandomDiaryResponse.from(diary);
    }

    public DiaryListResponse getDiaryList(Long userId) {

        // 1) 유저별 Diary 전체 조회
        List<Diary> diaries = diaryRepository.findAllByUserId(userId);

        // 2) DiaryListResponse 변환
        return DiaryListResponse.from(diaries);
    }
}
