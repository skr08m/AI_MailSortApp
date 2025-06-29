package com.example.AI_mailSortApp.service;

import java.io.IOException;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.AI_mailSortApp.model.Mail;
import com.example.AI_mailSortApp.model.SesMailData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.gmail.model.Message;

/**
 * 定期実行スケジューラー。各サービスを呼び出し
 * Gmailからメールを取得し、Geminiで解析し、DB更新までの流れを制御する
 */
@Service
public class GmailToGeminiScheduler {

    private final GmailService gmailService;
    private final GeminiService geminiService;
    private final MailService mailService;

    public GmailToGeminiScheduler(GmailService gmailService, GeminiService geminiService, MailService mailService) {
        this.gmailService = gmailService;
        this.geminiService = geminiService;
        this.mailService = mailService;
    }

    @Scheduled(fixedRate = 60000)
    public void fetchAndSendToGemini() {
        try {
            Long lastFetchedTime = mailService.fetchLastFetchedTime();
            List<Message> messages = gmailService.getMessagesAfter("me", lastFetchedTime);
            List<Mail> mails = mailService.convertMessagesToMails(messages, gmailService);

            if (mails.isEmpty()) {
                return;
            }

            for (Mail mail : mails) {
                String result = geminiService.generateText(mail.getMainText());
                SesMailData sesMailData = geminiService.convertJsonToSesMailData(result);// JSONからSesMailData型へ

            }

            mailService.saveLatestFetchedTime(mails);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
