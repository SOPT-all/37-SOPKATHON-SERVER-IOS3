package org.project.global.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.project.global.api.ErrorCode;
import org.project.global.api.GeneralException;

public class PromptUtil {
    public static String generatePrompt() {

        return """
                오늘의 일기 주제 딱 하나만 추천해줘.

                출력 형식(JSON):
                {
                    "questionList": "오늘의 주제"
                }
                """;
    }

    public static String extractJson(String response) {
        int startIdx = response.indexOf('{');
        int endIdx = response.lastIndexOf('}');

        if (startIdx == -1 || endIdx == -1 || endIdx <= startIdx) {
            throw new GeneralException(ErrorCode.GPT_RESPONSE_PARSE_ERROR);
        }

        String json = response.substring(startIdx, endIdx + 1);

        try {
            // JSON 파싱
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(json);

            // "questionList" 문자열만 반환
            if (!node.has("questionList")) {
                throw new GeneralException(ErrorCode.GPT_RESPONSE_PARSE_ERROR);
            }

            return node.get("questionList").asText();

        } catch (Exception e) {
            throw new GeneralException(ErrorCode.GPT_RESPONSE_PARSE_ERROR);
        }
    }
}
