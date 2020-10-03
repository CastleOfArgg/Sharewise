package com.kastle.sharewise.repository;

import com.kastle.sharewise.model.Transaction;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface for Restful access to stored Transactions
 */
public interface TransactionRepository extends CrudRepository<Transaction, Transaction.TransactionKey> {}
