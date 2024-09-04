package com.banorte.account.transactions.service;

import com.banorte.account.transactions.repository.TransactionsRepository;
import com.banorte.account.transactions.repository.entity.TransactionEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class TransactionsService {

  private final TransactionsRepository transactionsRepository;

  public TransactionsService(
      TransactionsRepository transactionsRepository
  ) {
    this.transactionsRepository = transactionsRepository;
  }

  public Flux<TransactionEntity> getAll(String type) {
    return switch (type) {
      case String t when "WITHDRAW".equals(t) || "INCOME".equals(t) -> this.transactionsRepository.findByType(t);
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
