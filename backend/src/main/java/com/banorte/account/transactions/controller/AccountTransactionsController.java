package com.banorte.account.transactions.controller;

import com.banorte.account.transactions.repository.entity.TransactionEntity;
import com.banorte.account.transactions.service.TransactionsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/transactions")
public class AccountTransactionsController {

    private final TransactionsService transactionsService;

    public AccountTransactionsController(
        TransactionsService transactionsService
    ) {
        this.transactionsService = transactionsService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TransactionEntity> getAll() {
        return this.transactionsService.getAll();
    }
}
