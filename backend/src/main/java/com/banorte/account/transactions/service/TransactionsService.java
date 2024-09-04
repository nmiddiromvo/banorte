package com.banorte.account.transactions.service;

import com.banorte.account.transactions.repository.TransactionsRepository;
import com.banorte.account.transactions.repository.entity.TransactionEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionsService {

  private final TransactionsRepository transactionsRepository;

  public TransactionsService(
      TransactionsRepository transactionsRepository
  ) {
    this.transactionsRepository = transactionsRepository;
  }

  public Flux<TransactionEntity> getAll() {
    return this.transactionsRepository.findAll();
  }

  public Mono<TransactionEntity> create(TransactionEntity transactionEntity) {
    return this.transactionsRepository.save(transactionEntity);
  }
}
