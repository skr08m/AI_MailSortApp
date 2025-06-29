package com.example.AI_mailSortApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mail")
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mail_id")
    private Integer mailId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "lower_salary")
    private Integer lowerSalary;

    @Column(name = "min_salary")
    private Integer minSalary;

    @Column(name = "max_age")
    private Integer maxAge;

    @Column(name = "sent_at")
    private long sentAt;// UNIX時間

    @Column(name = "contract_start_date")
    private Integer contractStartDate;

    @Column(name = "contract_end_date")
    private Integer contractEndDate;

    @Column(name = "employment_type")
    private String employmentType;

    @Column(name = "working_days")
    private Integer workingDays;

    @Column(name = "interview_count")
    private Integer interviewCount;

    @Column(name = "transaction_flow_code")
    private Integer transactionFlowCode;

    @Column(name = "work_location")
    private String workLocation;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "mainText")
    private String mainText;// DBカラム未実装、テスト時注意

    @Column(name = "link")
    private String link;

    // デフォルトコンストラクタ（JPA用）
    public Mail() {
    }

    public Mail(Long sentAt, String mainText, String link) {
        this.sentAt = sentAt;
        this.mainText = mainText;
        this.link = link;
    }

    // ===== Getter & Setter =====

    public Integer getMailId() {
        return mailId;
    }

    public void setMailId(Integer mailId) {
        this.mailId = mailId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getLowerSalary() {
        return lowerSalary;
    }

    public void setLowerSalary(Integer lowerSalary) {
        this.lowerSalary = lowerSalary;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public long getSentAt() {
        return sentAt;
    }

    public void setSentAt(long sentAt) {
        this.sentAt = sentAt;
    }

    public Integer getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Integer contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Integer getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Integer contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public Integer getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(Integer workingDays) {
        this.workingDays = workingDays;
    }

    public Integer getInterviewCount() {
        return interviewCount;
    }

    public void setInterviewCount(Integer interviewCount) {
        this.interviewCount = interviewCount;
    }

    public Integer getTransactionFlowCode() {
        return transactionFlowCode;
    }

    public void setTransactionFlowCode(Integer transactionFlowCode) {
        this.transactionFlowCode = transactionFlowCode;
    }

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}