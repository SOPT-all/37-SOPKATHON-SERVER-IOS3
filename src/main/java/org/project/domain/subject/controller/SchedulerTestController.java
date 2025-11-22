package org.project.domain.subject.controller;

import lombok.RequiredArgsConstructor;
import org.project.domain.subject.scheduler.GptScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class SchedulerTestController {

    private final GptScheduler gptScheduler;

    @GetMapping("/run-gpt-scheduler")
    public String runScheduler() {
        gptScheduler.runDailyGptPrompt();
        return "스케줄러 강제 실행 완료!";
    }
}
