package com.example.AI_mailSortApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "email_required_skillset")
public class EmailRequiredSkillset {

    @EmbeddedId
    private EmailRequiredSkillsetId id;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill_require", nullable = false)
    private SkillRequire skillRequire;
    

    public EmailRequiredSkillset(EmailRequiredSkillsetId id, SkillRequire skillRequire) {
        this.id = id;
        this.skillRequire = skillRequire;
    }

    // ===== Getter & Setter =====

    public EmailRequiredSkillsetId getId() {
        return id;
    }

    public void setId(EmailRequiredSkillsetId id) {
        this.id = id;
    }

    public SkillRequire getSkillRequire() {
        return skillRequire;
    }

    public void setSkillRequire(SkillRequire skillRequire) {
        this.skillRequire = skillRequire;
    }
}