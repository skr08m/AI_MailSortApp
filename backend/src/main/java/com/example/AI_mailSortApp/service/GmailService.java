package com.example.AI_mailSortApp.service;

import java.io.IOException;
import java.util.*;

import com.example.AI_mailSortApp.client.GmailClient;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import org.springframework.stereotype.Service;

@Service
public class GmailService {

    private final GmailClient gmailClient;

    public GmailService(GmailClient gmailClient) {
        this.gmailClient = gmailClient;
    }

    public List<Message> getMessagesAfter(String userId, long afterEpochSeconds) throws IOException {
        List<Message> messageStubs = gmailClient.getListMessages(userId, afterEpochSeconds);

        if (messageStubs == null)
            return List.of(); // ← nullチェックは必須

        List<Message> fullMessages = new ArrayList<>();
        for (Message stub : messageStubs) {
            Message fullMessage = gmailClient.getMessage(userId, stub.getId());
            if (fullMessage != null && fullMessage.getInternalDate() != null) {
                fullMessages.add(fullMessage);
            }
        }
        return fullMessages;
    }

    // メール本文（text/plain）を取得
    public String getPlainTextBody(Message message) {
        if (message == null || message.getPayload() == null)
            return "(No Body)";

        MessagePart payload = message.getPayload();

        if ("text/plain".equals(payload.getMimeType()) && payload.getBody() != null) {
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

    // Base64 デコードユーティリティ
    private String decodeBase64(String data) {
        return new String(Base64.getUrlDecoder().decode(data));
    }
}