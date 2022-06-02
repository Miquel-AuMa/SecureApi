package dev.auma.secure_api.service.impl;

import dev.auma.secure_api.exception.TransactionNotFoundException;
import dev.auma.secure_api.model.Transaction;
import dev.auma.secure_api.repository.TransactionRepository;
import dev.auma.secure_api.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getTransactions() {
        log.info("get all transactions");
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(Long id) throws TransactionNotFoundException {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()){
            log.info("get transaction {}", id);
            return transaction.get();
        } else {
            log.error("transaction {} not found", id);
            throw new TransactionNotFoundException("Failed search: transaction not found");
        }
    }

    @Override
    public Transaction getTransactionByName(String name) throws TransactionNotFoundException{
        Optional<Transaction> transaction = transactionRepository.findByName(name);
        if (transaction.isPresent()){
            log.info("get transaction {}", name);
            return transaction.get();
        } else {
            log.error("transaction {} not found", name);
            throw new TransactionNotFoundException("Failed search: transaction not found");
        }
    }

    @Override
    public Transaction saveNote(Transaction transaction) {
        log.info("trying to save transaction {}", transaction.toString());
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        log.info("trying to delete transaction {}", id);
        transactionRepository.deleteById(id);
        log.info("transaction {} deleted", id);
    }

    @Override
    public void updateTransaction(Long id, Transaction transaction) throws TransactionNotFoundException {
        Optional<Transaction> optionalNote = transactionRepository.findById(id);
        if (optionalNote.isPresent()){
            log.info("trying to update transaction {}", id);
            transactionRepository.save(transaction);
            log.info("transaction {} updated", id);
        } else {
            log.error("transaction {} not found", id);
            throw new TransactionNotFoundException("Failed update: transaction not found");
        }
    }
}
