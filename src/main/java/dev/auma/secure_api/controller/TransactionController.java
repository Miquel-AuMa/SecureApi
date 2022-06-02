package dev.auma.secure_api.controller;

import dev.auma.secure_api.exception.TransactionNotFoundException;
import dev.auma.secure_api.model.Transaction;
import dev.auma.secure_api.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public List<Transaction> findAll() {
        return transactionService.getTransactions();
    }

    //TODO: capture exceptions
    @GetMapping("/name/{name}")
    public Transaction findByName(@PathVariable String name) throws TransactionNotFoundException {
        return transactionService.getTransactionByName(name);
    }

    //TODO: capture exceptions
    @GetMapping("/id/{id}")
    public Transaction findById(@PathVariable Long id) throws TransactionNotFoundException{
        return transactionService.getTransactionById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Transaction> create(@RequestBody Transaction transaction) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/transaction/save").toUriString());
        return ResponseEntity.created(uri).body(transactionService.saveNote(transaction));
    }

    //TODO: capture exceptions
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody Transaction transaction) throws TransactionNotFoundException {
        transactionService.updateTransaction(id, transaction);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

}
