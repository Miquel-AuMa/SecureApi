package dev.auma.secure_api;

import dev.auma.secure_api.model.Transaction;
import dev.auma.secure_api.service.TransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
public class SecureApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureApiApplication.class, args);
	}

	// Adds data to the database at application startup
	@Bean
	CommandLineRunner initializeData(TransactionService transactionService) {
		return (args -> {
			transactionService.saveNote(new Transaction(null, "transaction-01", "Some random text", new Date()));
			transactionService.saveNote(new Transaction(null, "transaction-02", "Some random text", new Date()));
			transactionService.saveNote(new Transaction(null, "transaction-03", "Some random text", new Date()));
			transactionService.saveNote(new Transaction(null, "transaction-04", "Some random text", new Date()));

			System.out.println(transactionService.getTransactions());
		});
	}

}
