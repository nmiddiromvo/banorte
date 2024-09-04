package com.banorte.account.transactions.controller;

import com.banorte.account.transactions.repository.entity.TransactionEntity;
import com.banorte.account.transactions.service.TransactionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transactions")
public class AccountTransactionsController {

    private final TransactionsService transactionsService;

    public AccountTransactionsController(
        TransactionsService transactionsService
    ) {
        this.transactionsService = transactionsService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TransactionEntity> getAll() {
        return this.transactionsService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<TransactionEntity> create(TransactionEntity transactionEntity) {
        return this.transactionsService.create(transactionEntity);
    }
}
