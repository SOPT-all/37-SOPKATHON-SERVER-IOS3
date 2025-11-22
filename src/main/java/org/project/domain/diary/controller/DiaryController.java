package org.project.domain.diary.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.domain.diary.dto.request.PostDiaryRequest;
import org.project.domain.diary.dto.reponse.DiaryListResponse;
import org.project.domain.diary.dto.reponse.RandomDiaryResponse;
import org.project.domain.diary.dto.request.EmotionRequest;
import org.project.domain.diary.service.DiaryService;
import org.project.global.api.ApiResponse;
import org.springframework.http.ResponseEntity;

import org.project.domain.diary.dto.request.PostDiaryRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/diarys")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;


    @PostMapping
    @Operation(summary = "일기 작성",
    description = "사용자가 일기를 새로 작성합니다.")
    public ResponseEntity<ApiResponse<?>> postDiary(
            @RequestBody PostDiaryRequest request
    ) {

        log.info(request.toString());
        diaryService.postDiary(request);
        return ResponseEntity.ok().body(
                ApiResponse.created(null)
        );
    }
    @GetMapping("/random")
    @Operation(summary = "랜덤 일기 조회",
    description = "다른 사용자가 작성한 랜덤 일기를 하나 조회합니다.")
    public ResponseEntity<ApiResponse<RandomDiaryResponse>> getRandomDiary() {
        RandomDiaryResponse response = diaryService.getRandomDiary();
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "유저의 일기 목록 조회",
    description = "특정 유저의 전체 일기 목록을 조회합니다.")
    public ResponseEntity<ApiResponse<DiaryListResponse>> getDiaryList(@PathVariable Long userId) {

        DiaryListResponse response = diaryService.getDiaryList(userId);

        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping("/{diaryId}/emotion")
    @Operation(summary = "이모지를 눌러 카운트 증가",
    description = "감정 타입(heart, good, tear, clap, fire) 중 하나를 전달하면, 해당 일기의 감정 카운트가 1 증가합니다.")
    public ResponseEntity<ApiResponse<Void>> increaseEmotion(
            @PathVariable Long diaryId,
            @RequestBody EmotionRequest request
    ) {

        diaryService.increaseEmotion(diaryId, request.emotion());

        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
