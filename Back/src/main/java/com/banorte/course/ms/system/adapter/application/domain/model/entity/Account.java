package com.banorte.course.ms.system.adapter.application.domain.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSequence")
    private long id;
    private BigDecimal balance;
    private String type;
    private long customerId;


}
