package com.example.AI_mailSortApp.client;

import com.google.genai.Client;

import jakarta.annotation.PostConstruct;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeminiClient {

    private Client client;

    GeminiClient() {

    }

    @Value("${gemini.api.key}")
    private String apiKey;

    @PostConstruct
    public void init() {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("gemini.api.key が設定されていません");
        }

        this.client = new Client.Builder()
                .apiKey(apiKey)
                .build();
    }

    public String generateText(String prompt) throws IOException {
        String result = client.models.generateContent("gemini-2.5-flash-lite-preview-06-17", prompt, null).text();
        System.out.println(result);
        return result;
    }
}
