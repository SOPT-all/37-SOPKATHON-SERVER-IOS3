package org.project.domain.subject.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.project.domain.subject.dto.response.SubjectResponse;
import org.project.domain.subject.service.SubjectService;
import org.project.global.api.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping()
    @Operation(summary = "데일리 주제 조회", description = "ai가 매일 자정 생성하는 주제를 조회합니다.")
    public ResponseEntity<ApiResponse<SubjectResponse>> getSubject() {
        SubjectResponse response = subjectService.getSubject();
        return ResponseEntity.ok(ApiResponse.ok((response)));
    }
}
