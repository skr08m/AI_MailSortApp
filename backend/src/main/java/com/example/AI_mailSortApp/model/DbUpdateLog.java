package com.example.AI_mailSortApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "db_update_log")
public class DbUpdateLog {

    @Id
    @Column(name = "id", nullable = false)
    private Boolean id = true; // 常にtrueとする

    @Column(name = "last_fetched_id", nullable = false)
    private String lastFetchedId;

    @Column(name = "time", nullable = false)
    private Long time; // UNIX時間

    public DbUpdateLog(String lastFetchedId, Long time) {
        this.id = true; // コンストラクタでもtrue固定
        this.lastFetchedId = lastFetchedId;
        this.time = time;
    }

    // ===== Getter & Setter =====

    public String getLastFetchedId() {
        return lastFetchedId;
    }

    public void setLastFetchedId(String lastFetchedId) {
        this.lastFetchedId = lastFetchedId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
