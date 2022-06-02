package dev.auma.secure_api.service.impl;

import dev.auma.secure_api.exception.TransactionNotFoundException;
import dev.auma.secure_api.model.Transaction;
import dev.auma.secure_api.repository.TransactionRepository;
import dev.auma.secure_api.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(Long id) throws TransactionNotFoundException {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()){
            return transaction.get();
        } else {
            throw new TransactionNotFoundException("Failed search: transaction not found");
        }
    }

    @Override
    public Transaction getTransactionByName(String name) throws TransactionNotFoundException{
        Optional<Transaction> transaction = transactionRepository.findByName(name);
        if (transaction.isPresent()){
            return transaction.get();
        } else {
            throw new TransactionNotFoundException("Failed search: transaction not found");
        }
    }

    @Override
    public Transaction saveNote(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public void updateTransaction(Long id, Transaction transaction) throws TransactionNotFoundException {
        Optional<Transaction> optionalNote = transactionRepository.findById(id);
        if (optionalNote.isPresent()){
            transactionRepository.save(transaction);
        } else {
            throw new TransactionNotFoundException("Failed update: transaction not found");
        }
    }
}
