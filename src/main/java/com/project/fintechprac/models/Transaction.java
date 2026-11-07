package com.project.fintechprac.models;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "transactions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)

public class Transaction extends BaseEntity {
    @Column(name = "external_id", length = 64, nullable = false, unique = true, updatable = false)
    private String externalId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", updatable = false, nullable = false)
    private Account account;
    @Column(name = "amount", precision = 19, scale = 4, nullable = false, updatable = false)
    private BigDecimal amount;
    @Column(name = "currency", length = 3, updatable = false, nullable = false)
    private String currency;
    @Column(name = "merchant_name", updatable = false, nullable = false, length = 255)
    private String merchantName;
    @Column(name = "mcc", length = 4, updatable = false)
    private String mcc;
    @Column(name = "country", length = 2, updatable = false, nullable = false)
    private String country;
    @Column(name = "device_id", length = 128, updatable = false)
    private String deviceId;
    @Column(name = "ip", length = 45, updatable = false)
    private String ip;
    @Column(name = "occurred_at", nullable = false, updatable = false)
    private Instant occurredAt;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void onCreate(){
        if (createdAt == null ) createdAt = Instant.now();
    }


}
