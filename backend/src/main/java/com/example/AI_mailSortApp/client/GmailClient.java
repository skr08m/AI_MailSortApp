package com.example.AI_mailSortApp.client;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Component
public class GmailClient {

    private final Gmail gmailService;

    public GmailClient(Gmail gmailService) {
        this.gmailService = gmailService;
    }

    // メッセージの一覧取得
    public List<Message> listMessages(String userId, long lastFetchedTime) throws IOException {
        return gmailService.users().messages()
                .list(userId)
                .setQ("after:" + lastFetchedTime) // 例: "after:1704067200" 2024-01-01 00:00:00 UTC
                .execute()
                .getMessages();
    }

    // メッセージ詳細取得
    public Message getMessage(String userId, String messageId) throws IOException {
        return gmailService.users().messages().get(userId, messageId).execute();
    }

    // メール本文（text/plain）を取得
    public String getPlainTextBody(Message message) {
        if (message == null || message.getPayload() == null)
            return "(No Body)";

        MessagePart payload = message.getPayload();

        // シンプルな text/plain の本文を探す（multipart 対応）
        if (payload.getMimeType().equals("text/plain") && payload.getBody() != null) {
            return decodeBase64(payload.getBody().getData());
        } else if (payload.getParts() != null) {
            for (MessagePart part : payload.getParts()) {
                if ("text/plain".equals(part.getMimeType()) && part.getBody() != null) {
                    return decodeBase64(part.getBody().getData());
                }
            }
        }

        return "(No Body Found)";
    }

    // メールの受信日時を日本時間で返す
    public long getInternalDateAsString(Message message) {
        if (message == null || message.getInternalDate() == null) {
            return -1;
        }

        long timestampMillis = message.getInternalDate(); // ミリ秒単位のUNIX時間
        return timestampMillis;
    }

    // Base64 デコードユーティリティ
    private String decodeBase64(String data) {
        return new String(Base64.getUrlDecoder().decode(data));
    }
}