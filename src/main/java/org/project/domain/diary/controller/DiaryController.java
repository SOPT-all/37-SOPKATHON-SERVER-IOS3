package org.project.domain.diary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.domain.diary.dto.request.PostDiaryRequest;
import org.project.domain.diary.service.DiaryService;
import org.project.global.api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.project.domain.diary.dto.reponse.DiaryListResponse;
import org.project.domain.diary.dto.reponse.RandomDiaryResponse;
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
    public ResponseEntity<ApiResponse<RandomDiaryResponse>> getRandomDiary() {
        RandomDiaryResponse response = diaryService.getRandomDiary();
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<DiaryListResponse>> getDiaryList(@PathVariable Long userId) {

        DiaryListResponse response = diaryService.getDiaryList(userId);

        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
