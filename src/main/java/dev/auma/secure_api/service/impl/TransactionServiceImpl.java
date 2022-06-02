package dev.auma.secure_api.service.impl;

import dev.auma.secure_api.model.Transaction;
import dev.auma.secure_api.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Override
    public List<Transaction> getTransactions() {
        return null;
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return null;
    }

    @Override
    public Transaction getTransactionByName(String name) {
        return null;
    }

    @Override
    public Transaction saveNote(Transaction transaction) {
        return null;
    }

    @Override
    public void deleteTransaction(Long id) {

    }

    @Override
    public void updateTransaction(Long id, Transaction transaction) {

    }
}
