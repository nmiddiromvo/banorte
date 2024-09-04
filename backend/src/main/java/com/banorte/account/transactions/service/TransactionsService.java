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

  public Flux<TransactionEntity> getAll(TransactionEntity.TransactionType type) {
    return switch (type) {
      case TransactionEntity.TransactionType t
          when TransactionEntity.TransactionType.WITHDRAW.equals(t) || TransactionEntity.TransactionType.INCOME.equals(t)
                -> this.transactionsRepository.findByType(t);
      case null -> this.transactionsRepository.findAll();
      default -> this.transactionsRepository.findAll();
    };
  }

  public Mono<Void> toggleFavorite(final Long transactionId) {
    return this.transactionsRepository.findById(transactionId)
        .flatMap(transactionEntity -> {
          transactionEntity.setFavorite(!transactionEntity.getFavorite());

          return this.transactionsRepository.save(transactionEntity);
        })
        .then();
  }

  public Mono<Void> delete(final Long transactionId) {
    return this.transactionsRepository.deleteById(transactionId);
  }
}
