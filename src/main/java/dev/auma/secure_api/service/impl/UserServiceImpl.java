package dev.auma.secure_api.service.impl;

import dev.auma.secure_api.exception.RoleNotFoundException;
import dev.auma.secure_api.exception.UserNotFoundException;
import dev.auma.secure_api.model.Role;
import dev.auma.secure_api.model.User;
import dev.auma.secure_api.repository.RoleRepository;
import dev.auma.secure_api.repository.UserRepository;
import dev.auma.secure_api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) throws UserNotFoundException, RoleNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        Optional<Role> role = roleRepository.findByName(roleName);
        if (!user.isPresent()){
            throw new UserNotFoundException("user " + username + " not found");
        } else if (!role.isPresent()){
            throw new RoleNotFoundException("role " + roleName + " not found");
        } else {
            user.get().getRoles().add(role.get());
        }
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            return user.get();
        } else {
            throw new UserNotFoundException("user " + username + " not found");
        }
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
