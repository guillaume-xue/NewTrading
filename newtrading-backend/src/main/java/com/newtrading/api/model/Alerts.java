package com.newtrading.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import com.newtrading.api.model.TriggerCondition;
import com.newtrading.api.model.NotificationChannel;


@Entity
@Table(name = "alerts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alerts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "asset_code", length = 12, nullable = false)
    private String assetCode;

    @Column(name = "target_price", precision = 18, scale = 8, nullable = false)
    private BigDecimal targetPrice;

    @Column(name = "trigger_condition", length = 10, nullable = false)
    private TriggerCondition triggerCondition;

    @Enumerated(EnumType.STRING)
    @Column(name = "channel", length = 10, nullable = false)
    private NotificationChannel channel;

    @Column(name = "is_triggered", nullable = false)
    private Boolean isTriggered;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = OffsetDateTime.now();
        }
    }
}
