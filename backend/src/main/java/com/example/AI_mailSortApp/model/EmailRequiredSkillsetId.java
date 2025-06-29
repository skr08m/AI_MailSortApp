package com.example.AI_mailSortApp.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable // 主キークラス
public class EmailRequiredSkillsetId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "mail_id")
    private Integer mailId;

    @Column(name = "skill_id")
    private Integer skillId;

    public EmailRequiredSkillsetId(Integer mailId, Integer skillId) {
        this.mailId = mailId;
        this.skillId = skillId;
    }

    // ===== Getter & Setter =====

    public Integer getMailId() {
        return mailId;
    }

    public void setMailId(Integer mailId) {
        this.mailId = mailId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof EmailRequiredSkillsetId))
            return false;
        EmailRequiredSkillsetId that = (EmailRequiredSkillsetId) o;
        return Objects.equals(mailId, that.mailId) &&
                Objects.equals(skillId, that.skillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mailId, skillId);
    }
}