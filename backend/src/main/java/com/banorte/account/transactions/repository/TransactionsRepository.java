package com.banorte.account.transactions.repository;

import com.banorte.account.transactions.repository.entity.TransactionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TransactionsRepository extends ReactiveCrudRepository<TransactionEntity, Long> {

  Flux<TransactionEntity> findByType(TransactionEntity.TransactionType type);
}
