package org.project.domain.subject.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.global.client.GptClient;
import org.project.global.util.PromptUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GptScheduler {

    private final GptClient gptClient;

    /**
     * 매일 밤 22시에 실행
     */
    @Scheduled(cron = "0 0 22 * * ?")
    @Async   // 비동기 실행
    public void runDailyGptPrompt() {

        log.info("GPT 일기 주제 생성 시작 (22:00)");

        try {
            String prompt = PromptUtil.generatePrompt();

            String gptResponse = gptClient.callOpenAI(prompt);
            log.info("GPT 원본 응답 = {}", gptResponse);

            // JSON 내용만 추출
            String question = PromptUtil.extractJson(gptResponse);

            log.info("추출된 오늘의 일기 주제 = {}", question);

            // TODO: DB 저장하거나 캐싱하고 싶으면 여기서 처리해
            // diarySubjectRepository.save(new DiarySubject(question));

        } catch (Exception e) {
            log.error("GPT 스케줄러 실행 오류", e);
        }
    }
}
