package dev.auma.secure_api.repository;

import dev.auma.secure_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
