package dev.auma.secure_api.service;

import dev.auma.secure_api.exception.RoleNotFoundException;
import dev.auma.secure_api.exception.UserNotFoundException;
import dev.auma.secure_api.model.Role;
import dev.auma.secure_api.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserByUsername(String username) throws UserNotFoundException;
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName) throws UserNotFoundException, RoleNotFoundException;
}
