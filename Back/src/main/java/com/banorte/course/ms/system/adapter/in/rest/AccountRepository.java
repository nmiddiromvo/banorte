package com.banorte.course.ms.system.adapter.in.rest;

import com.banorte.course.ms.system.adapter.application.domain.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
