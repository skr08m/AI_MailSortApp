package com.example.AI_mailSortApp.client;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class GeminiClient {
    private final Client client;

    public GeminiClient() {
        if (System.getenv("GOOGLE_API_KEY") == null) {
            throw new IllegalStateException("環境変数 GOOGLE_API_KEY が設定されていません。");
        }
        this.client = new Client();
    }

    public String generateText(String prompt) throws IOException {
        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash-lite-preview-06-17", // このバージョンでの動作未確認
                prompt,
                null);
        return response.text();
    }
}
