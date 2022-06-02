package dev.auma.secure_api.repository;

import dev.auma.secure_api.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByName(String name);
}
