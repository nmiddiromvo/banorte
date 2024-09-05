package com.banorte.course.ms.system.adapter.application.domain.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movementSequence")
    private long id;
    private BigDecimal amount;
    private LocalDateTime movementDate;
    private String type;
    private boolean isFavorite;
    private String concept;
    private long accountId;

}
