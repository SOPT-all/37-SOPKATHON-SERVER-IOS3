package org.project.global.google;

import com.google.cloud.ServiceOptions;
import com.google.cloud.language.v2.*;
import com.google.cloud.translate.v3.TranslateTextRequest;
import com.google.cloud.translate.v3.TranslateTextResponse;
import com.google.cloud.translate.v3.TranslationServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class GoogleApiComponent {

    public String classifyTag(String content) {
        try (LanguageServiceClient language = LanguageServiceClient.create()) {

            Document doc = Document.newBuilder()
                    .setContent(content)
                    .setType(Document.Type.PLAIN_TEXT)
                    .build();

            ClassifyTextRequest request = ClassifyTextRequest.newBuilder()
                    .setDocument(doc)
                    .build();

            ClassifyTextResponse response = language.classifyText(request);

            log.info(response.toString());
            List<String> categories = response.getCategoriesList().stream()
                    .map(ClassificationCategory::getName)
//                    .map(name -> {
//                        try {
//                            return translate(name.split("/")[0]);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    })
                    .map(name -> name.split("/")[1])
                    .toList();

            log.info(categories.toString());
            if(categories.isEmpty()) return null;
            return String.join(",", categories);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String translate(String text) throws IOException {
        try (TranslationServiceClient client = TranslationServiceClient.create()) {

            String projectId = ServiceOptions.getDefaultProjectId();
            String parent = String.format("projects/%s/locations/global", projectId);

            TranslateTextRequest request = TranslateTextRequest.newBuilder()
                    .setParent(parent)
                    .setMimeType("text/plain")
                    .setSourceLanguageCode("en")
                    .setTargetLanguageCode("ko")
                    .addContents(text)
                    .build();

            TranslateTextResponse response = client.translateText(request);
            log.info(response.getTranslationsList().get(0).getTranslatedText());
            return response.getTranslationsList().get(0).getTranslatedText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

