package org.project.global.google;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.ServiceOptions;
import com.google.cloud.language.v2.*;
import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.TranslationServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class GoogleApiComponent {

    public String generateTagClassifyByGemini(String content) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-lite:generateContent?key=AIzaSyB2CC6WSYe-loFfxbyKt-9Lx4YJmd3i-d0";

        RestTemplate rest = new RestTemplate();

        String body = """
        {
          "contents": [
            {
              "parts": [
                {
                  "text": "당신은 일기 내용을 분석해 핵심 주제를 파악하고, 그 주제를 대표할 수 있는 태그를 2~3개 생성하는 역할입니다.\\n\\n일기 내용: \\"%s\\"\\n\\n요구사항:\\n- 태그는 일기 내용의 핵심 감정, 사건, 주제를 반영할 것\\n- 태그는 한국어로 작성할 것\\n- 태그는 2~3개\\n- 쉼표(,)로 구분된 문자열로 반환\\n- 불필요한 설명 없이 태그만 출력\\n-공백은 _ 사용하지말고 공백으로만 사용\\n\\n태그:"
                }
              ]
            }
          ]
        }
        """.formatted(content);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            String response = rest.postForObject(url, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            return root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

