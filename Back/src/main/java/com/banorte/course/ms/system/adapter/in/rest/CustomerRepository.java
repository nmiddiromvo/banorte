package com.banorte.course.ms.system.adapter.in.rest;

import com.banorte.course.ms.system.adapter.application.domain.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
