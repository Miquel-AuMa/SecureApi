package dev.auma.secure_api.service;

import dev.auma.secure_api.model.Role;
import dev.auma.secure_api.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUserByUsername(String username);
    List<User> getUsers();
}
