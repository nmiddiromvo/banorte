package com.banorte.account.transactions.repository;

import com.banorte.account.transactions.repository.entity.TransactionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TransactionsRepository extends ReactiveCrudRepository<TransactionEntity, Long> {
}
