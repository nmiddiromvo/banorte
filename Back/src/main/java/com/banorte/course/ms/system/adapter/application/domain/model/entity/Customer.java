package com.banorte.course.ms.system.adapter.application.domain.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSequence")
    private long id;
    private String firstName;
    private String lastName;
}
