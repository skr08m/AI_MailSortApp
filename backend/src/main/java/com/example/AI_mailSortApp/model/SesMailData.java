package com.example.AI_mailSortApp.model;

import java.util.List;

import lombok.Data;

@Data
public class SesMailData {
    private String メール内容;
    private String 案件名;
    private String 単価下限;
    private String 単価上限;
    private String 勤務地;
    private String 稼働率;
    private String 契約期間;
    private List<String> 必須スキル;
    private List<String> 尚可スキル;
    private String 商流;
    private String 面談回数;
    private String リモート可否;
    private String 年齢制限;
    private String 会社名;
    private String 担当者名;
}
