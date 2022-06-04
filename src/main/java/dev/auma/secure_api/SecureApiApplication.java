package dev.auma.secure_api;

import dev.auma.secure_api.model.Role;
import dev.auma.secure_api.model.Transaction;
import dev.auma.secure_api.model.User;
import dev.auma.secure_api.service.TransactionService;
import dev.auma.secure_api.service.UserService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class SecureApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureApiApplication.class, args);
	}

	// TODO: move to a proper place (OpenAPIConfiguration?)
	@Bean
	public OpenAPI customOpenAPI(@Value("${application-description}") String appDescription, @Value("${application-version}") String appVersion) {
		return new OpenAPI()
				.info(new Info()
						.title("Secure API")
						.version(appVersion)
						.description(appDescription)
						.termsOfService("http://swagger.io/terms/")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

	// TODO: move to a proper place (utilities class?)
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	// Adds basic data to the database at application startup
	@Bean
	CommandLineRunner initializeData(TransactionService transactionService, UserService userService) {
		return (args -> {
			// Creation of the transactions information
			transactionService.saveNote(new Transaction(null, "transaction-01", "Some random text", new Date()));
			transactionService.saveNote(new Transaction(null, "transaction-02", "Some random text", new Date()));
			transactionService.saveNote(new Transaction(null, "transaction-03", "Some random text", new Date()));
			transactionService.saveNote(new Transaction(null, "transaction-04", "Some random text", new Date()));

			System.out.println(transactionService.getTransactions());

			// Creation of the users and roles information
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			userService.saveUser(new User(null, "User", "user", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Admin", "admin", "1234", new ArrayList<>()));

			userService.addRoleToUser("user", "ROLE_USER");
			userService.addRoleToUser("admin", "ROLE_ADMIN");

			System.out.println(userService.getUsers());
		});
	}

}
