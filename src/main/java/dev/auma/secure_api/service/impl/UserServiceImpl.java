package dev.auma.secure_api.service.impl;

import dev.auma.secure_api.model.Role;
import dev.auma.secure_api.model.User;
import dev.auma.secure_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public Role saveRole(Role role) {
        return null;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }
}
