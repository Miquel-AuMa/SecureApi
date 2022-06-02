package dev.auma.secure_api.repository;

import dev.auma.secure_api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
