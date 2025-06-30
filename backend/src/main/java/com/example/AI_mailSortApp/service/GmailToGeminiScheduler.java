package com.example.AI_mailSortApp.service;

import java.io.IOException;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.AI_mailSortApp.model.Mail;
import com.example.AI_mailSortApp.model.SesMailData;
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
    private final int GEMINI_API_LIMIT_PER_MINUTES = 15;

    public GmailToGeminiScheduler(GmailService gmailService, GeminiService geminiService, MailService mailService) {
        this.gmailService = gmailService;
        this.geminiService = geminiService;
        this.mailService = mailService;
    }

    @Scheduled(fixedRate = 60000)
    public void fetchAndSendToGemini() {
        System.out.println("定期処理実行");

        try {
            Long lastFetchedTime = mailService.fetchLastFetchedTime();
            System.out.println("最終更新日時: " + lastFetchedTime);

            List<Message> messages = gmailService.getMessagesAfter("me", lastFetchedTime);// 完全なメッセージを持つMessageをAPIで取得
            System.out.println("Messageリストの取得完了");

            List<Mail> mails = mailService.convertMessagesToMails(messages);

            if (!(messages.size() == mails.size()))// 一応
                return;

            for (int i = 0; i < messages.size(); i++) {
                String mainText = gmailService.getPlainTextBody(messages.get(i));
                mails.get(i).setMainText(mainText);// メールオブジェクト(sentAt,mainText,linkをセットしたもの)ができる
            }
            System.out.println("メール取得完了");

            if (mails.isEmpty()) {
                return;
            }

            int apiRequestCount = 0;
            for (Mail mail : mails) {
                if (GEMINI_API_LIMIT_PER_MINUTES < apiRequestCount) {
                    break;
                }
                String result = geminiService.generateText(mail.getMainText());
                System.out.println(result);
                SesMailData sesMailData = geminiService.convertJsonToSesMailData(result);// JSONからSesMailData型へ
            }
            mailService.saveLatestFetchedTime(mails);

        } catch (IOException e) {
            if (e.getMessage().contains("429")) {
                System.err.println("APIリクエスト制限超過");
                // ログ記録やリトライ処理などを書く
            } else {
                e.printStackTrace();
            }
        }
    }
}
