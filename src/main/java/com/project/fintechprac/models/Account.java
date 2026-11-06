package com.project.fintechprac.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "accounts")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity {
    @Column(name = "external_id", nullable = false, unique = true, length = 64)
    private String externalId;
    @Column(name = "home_country", nullable = false, length = 2)
    @Setter(AccessLevel.NONE)
    private String homeCountry;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void onCreate() {
        if (createdAt == null ) createdAt = Instant.now();
    }
}
