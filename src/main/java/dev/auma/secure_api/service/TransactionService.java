package dev.auma.secure_api.service;

import dev.auma.secure_api.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactions();
    Transaction getTransactionById(Long id);
    Transaction getTransactionByName(String name);
    Transaction saveNote(Transaction transaction);
    void deleteTransaction(Long id);
    void updateTransaction(Long id, Transaction transaction);
}
