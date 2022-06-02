package dev.auma.secure_api.service;

import dev.auma.secure_api.exception.TransactionNotFoundException;
import dev.auma.secure_api.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactions();
    Transaction getTransactionById(Long id) throws TransactionNotFoundException;
    Transaction getTransactionByName(String name) throws TransactionNotFoundException;
    Transaction saveNote(Transaction transaction);
    void deleteTransaction(Long id);
    void updateTransaction(Long id, Transaction transaction) throws TransactionNotFoundException;
}
