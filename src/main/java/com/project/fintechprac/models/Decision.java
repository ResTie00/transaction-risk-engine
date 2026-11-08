package com.project.fintechprac.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "decisions")
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Decision extends BaseEntity{
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transaction_id", nullable = false, unique = true)
    private Transaction transaction;
    @Enumerated(EnumType.STRING)
    @Column(name = "outcome", nullable = false,updatable = false, length = 16)
    private Outcome outcome;
    @Column(name = "score", nullable = false, updatable = false)
    private int score;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "fired_rules", nullable = false, updatable = false)
    private List<FiredRule> firedRules;
    @Column(name = "decided_at", nullable = false, updatable = false)
    private Instant decidedAt;

    @PrePersist
    void prePersist(){
        if(decidedAt == null){decidedAt = Instant.now();}
    }

}
