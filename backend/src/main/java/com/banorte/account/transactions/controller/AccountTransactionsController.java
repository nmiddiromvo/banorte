package com.banorte.account.transactions.controller;

import com.banorte.account.transactions.repository.entity.TransactionEntity;
import com.banorte.account.transactions.service.TransactionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Flux<TransactionEntity> getAll(@Validated @RequestParam(value = "type", required = false) TransactionEntity.TransactionType type) {
        return this.transactionsService.getAll(type);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{transactionId}/favorite", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> toggleFavorite(@PathVariable("transactionId") Long transactionId) {
        return this.transactionsService.toggleFavorite(transactionId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{transactionId}")
    public Mono<Void> delete(@PathVariable("transactionId") Long transactionId) {
        return this.transactionsService.delete(transactionId);
    }
}
