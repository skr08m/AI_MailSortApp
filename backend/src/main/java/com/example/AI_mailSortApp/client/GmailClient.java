package com.example.AI_mailSortApp.client;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class GmailClient {

    private final Gmail gmail;

    public GmailClient(Gmail gmail) {
        this.gmail = gmail;
    }

    // メッセージの一覧取得
    public List<Message> getListMessages(String userId, long lastFetchedTime) throws IOException {
        return gmail.users().messages()
                .list(userId)
                .setQ("after:" + lastFetchedTime) // 例: "after:1704067200" 2024-01-01 00:00:00 UTC
                .execute()
                .getMessages();
    }

    // メッセージ詳細取得
    public Message getMessage(String userId, String messageId) throws IOException {
        return gmail.users().messages().get(userId, messageId).execute();
    }
}