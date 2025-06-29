package com.example.AI_mailSortApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "db_update_log")
public class DbUpdateLog {

    @Id
    @Column(name = "id", nullable = false)
    private Boolean id = true; // 常にtrueとする

    @Column(name = "time", nullable = false)
    private Long time; // UNIX時間

    public DbUpdateLog() {
        this.id = true;
    }

    public DbUpdateLog(Long time) {
        this.time = time;
    }

    // ===== Getter & Setter =====

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
