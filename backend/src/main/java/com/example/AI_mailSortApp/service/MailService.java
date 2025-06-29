package com.example.AI_mailSortApp.service;

import java.io.IOException;
import java.util.*;
import org.springframework.stereotype.Service;
import com.example.AI_mailSortApp.model.Mail;
import com.example.AI_mailSortApp.model.SesMailData;
import com.example.AI_mailSortApp.repository.DbUpdateLogRepository;
import com.example.AI_mailSortApp.model.DbUpdateLog;
import com.google.api.services.gmail.model.Message;

/**
 * Mailエンティティの変換、DB操作を担当するサービスクラス
 */
@Service
public class MailService {

    private final DbUpdateLogRepository dbUpdateLogRepository;

    public MailService(DbUpdateLogRepository dbUpdateLogRepository) {
        this.dbUpdateLogRepository = dbUpdateLogRepository;
    }

    // MessageからsentAt, mainText, link情報を持ったMailリストを返す
    public List<Mail> convertMessagesToMails(List<Message> messages, GmailService gmailService) {
        List<Mail> mails = new ArrayList<>();

        for (Message message : messages) {
            long sentAt = message.getInternalDate();
            String mainText = gmailService.getPlainTextBody(message);
            String link = "https://mail.google.com/mail/u/0/#inbox/" + message.getId();

            mails.add(new Mail(sentAt, mainText, link));
        }
        return mails;
    }

    // 最新の受信日時を持つMailをDB更新用に保存する
    public void saveLatestFetchedTime(List<Mail> mails) throws IOException {
        long latestTime = mails.stream()
                .map(Mail::getSentAt)
                .filter(Objects::nonNull)
                .mapToLong(Long::longValue)
                .max()
                .orElse(0L);
        dbUpdateLogRepository.save(new DbUpdateLog(latestTime));
    }

    // DBから最終更新時刻を取得。存在しなければ現在時刻の30日前を返す
    public Long fetchLastFetchedTime() throws IOException {
        return dbUpdateLogRepository.findById(true)
                .map(DbUpdateLog::getTime)
                .orElseGet(() -> java.time.ZonedDateTime.now(java.time.ZoneId.of("Asia/Tokyo"))
                        .minusDays(30).toEpochSecond());
    }

    // 案件の場合のMailオブジェクトを完成させる
    public Boolean convertSesMailToMail(Mail mail, SesMailData sesMailData) {
        if (!sesMailData.getメール内容().equals("案件"))
            return false;

        mail.setWorkLocation(sesMailData.get勤務地());
        // TODO 残りの情報のセット
        return true;
    }
}
