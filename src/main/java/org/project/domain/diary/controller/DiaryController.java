package org.project.domain.diary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.domain.diary.dto.PostDiaryRequest;
import org.project.domain.diary.service.DiaryService;
import org.project.global.api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/diarys")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;


    @PostMapping
    public ResponseEntity<ApiResponse<?>> postDiary(
            @RequestBody PostDiaryRequest request
    ) {
        diaryService.postDiary(request);
        return ResponseEntity.ok().body(
                ApiResponse.created(null)
        );
    }
}
