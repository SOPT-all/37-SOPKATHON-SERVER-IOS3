package org.project.global.google;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class GoogleApiComponent {

    public String generateTagClassifyByGemini(String content) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-lite:generateContent?key=AIzaSyB2CC6WSYe-loFfxbyKt-9Lx4YJmd3i-d0";

        RestTemplate rest = new RestTemplate();

        log.info("📌 [Gemini 태그 분류 요청 시작]");
        log.info("📝 입력 일기 내용 = {}", content);
        log.info("🔗 요청 URL = {}", url);

        String body = """
        {
          "contents": [
            {
              "parts": [
                {
                  "text": "당신은 일기 내용을 분석해 핵심 주제를 파악하고, 그 주제를 대표할 수 있는 태그를 2~3개 생성하는 역할입니다.\\n\\n일기 내용: \\"%s\\"\\n\\n요구사항:\\n- 태그는 일기 내용의 핵심 감정, 사건, 주제를 반영할 것\\n- 태그는 한국어로 작성할 것\\n- 태그는 2~3개\\n- 쉼표(,)로 구분된 문자열로 반환\\n- 불필요한 설명 없이 태그만 출력\\n- 공백은 _ 사용하지말고 공백으로만 사용\\n\\n태그:"
                }
              ]
            }
          ]
        }
        """.formatted(content);

        log.debug("📤 Gemini API 요청 Body = {}", body);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            String response = rest.postForObject(url, entity, String.class);

            log.info("📥 Gemini API 응답 원본 = {}", response);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            String tagResult = root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

            log.info("✨ 추출된 태그 결과 = {}", tagResult);

            return tagResult;

        } catch (Exception e) {
            log.error("❌ Gemini 태그 생성 중 오류 발생: {}", e.getMessage(), e);
            return null;
        }
    }
}
