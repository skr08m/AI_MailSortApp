package com.example.AI_mailSortApp.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.AI_mailSortApp.client.GmailClient;

@Service
public class GmailToGeminiScheduler {
    private final GmailClient gmailClient;

    GmailToGeminiScheduler(GmailClient gmailClient) {
        this.gmailClient = gmailClient;
    }

    @Scheduled(fixedRate = 60000) // 1分ごとに実行
    public void fetchAndSendToGemini() {

    }
}
