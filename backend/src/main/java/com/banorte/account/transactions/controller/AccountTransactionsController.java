package com.banorte.account.transactions.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class AccountTransactionsController {

    @GetMapping("/hello")
    public Mono<ResponseEntity<String>> hello() {
        return Mono.just(ResponseEntity.ok().body("Hello World"));
    }
}
