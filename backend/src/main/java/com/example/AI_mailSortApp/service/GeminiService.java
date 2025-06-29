package com.example.AI_mailSortApp.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.example.AI_mailSortApp.client.GeminiClient;
import com.example.AI_mailSortApp.model.SesMailData;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeminiService {
    private final GeminiClient geminiClient;
    private static final String PROMPT_TEMPLATE = "以下はSES案件の募集文です。\r\n" +
            "この中から以下の項目を抽出し、**JSON形式**で出力してください。\r\n" +
            "前置きはいいので、結果だけを出力してください。わからないなら、不明と記載してください。\r\n" +
            "出力はすべて文字列型とします。\r\n" +
            "【抽出してほしい項目】\r\n" +
            "- メール内容（案件情報のメールか、要員情報のメールであるか。案件or要員のどちらかで出力してください）\r\n" +
            "- 案件名\r\n" +
            "- 単価下限（万単位で数値１つのみorスキル見合いor不明のどれか）\r\n" +
            "- 単価上限（万単位で数値１つのみorスキル見合いor不明のどれか）\r\n" +
            "- 勤務地（都道府県で出力、その他の表記の場合は都道府県に直してください）\r\n" +
            "- 稼働率（週何日かで数値のみ）\r\n" +
            "- 契約期間（開始日、数値のみ(202507）\r\n" +
            "- 必須スキル（箇条書きで名詞のみ。正式名称が英語の物以外はなるべく日本語で各スキルは1単語で、頭文字を大文字にしてください。）\r\n" +
            "- 尚可スキル（同上（名詞・箇条書き・頭文字大文字））\r\n" +
            "- 商流（例：貴社社員まで、直案件、元請、2次請...を-1,0,1,2....として）\r\n" +
            "- 面談回数（回数の数値のみ）\r\n" +
            "- リモート可否（可／一部可／不可をremote,hybrid,officeのいずれかで出力）\r\n" +
            "- 年齢制限　（読み取れる最大の年齢を数値のみ出力）\r\n" +
            "- 会社名\r\n" +
            "- 担当者名\r\n" +
            "\r\n" +
            "以下が原文です：\r\n" +
            "\r\n" +
            "{ここに案件メール全文を貼る}\r\n" +
            "\r\n" +
            "【フォーマット例】\r\n" +
            "```json\r\n" +
            "{\r\n" +
            "メール内容: \"案件\",\r\n" +
            "案件名: \"AWS基盤構築支援案件\",\r\n" +
            "単価下限: \"70\",\r\n" +
            "単価上限: \"75\",\r\n" +
            "勤務地: \"東京\",\r\n" +
            "稼働率: \"5\",\r\n" +
            "契約期間: \"202508\",\r\n" +
            "必須スキル: [\"AWS\", \"EC2\", \"Lambda\", \"Python\", \"GitHub\", \"コミュニケーション能力\"],\r\n" +
            "尚可スキル: [\"Docker\", \"Zabbix\", \"リーダー\", \"要件定義\"],\r\n" +
            "商流: \"1\",\r\n" +
            "面談回数: \"1\",\r\n" +
            "リモート可否: \"remote\",\r\n" +
            "年齢制限: \"45\",\r\n" +
            "会社名: \"株式会社テックリンクス\",\r\n" +
            "担当者名: \"田中太郎\"\r\n" +
            "}\r\n" +
            "```";

    public GeminiService(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    public String generateText(String mainText) throws IOException {
        String prompt = PROMPT_TEMPLATE.replaceAll("\\{ここに案件メール全文を貼る\\}", mainText);
        return geminiClient.generateText(prompt);
    }

    public SesMailData convertJsonToSesMailData(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, SesMailData.class);
    }
}
