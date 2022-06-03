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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        log.info("get all users");
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            log.info("get user {}", username);
            return user.get();
        } else {
            log.error("user {} not found", username);
            throw new UserNotFoundException("user " + username + " not found");
        }
    }

    @Override
    public User saveUser(User user) {
        log.info("trying to save user {}", user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("trying to save role {}", role.toString());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) throws UserNotFoundException, RoleNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        Optional<Role> optionalRole = roleRepository.findByName(roleName);
        if (!optionalUser.isPresent()){
            log.error("user {} not found", username);
            throw new UserNotFoundException("user " + username + " not found");
        } else if (!optionalRole.isPresent()){
            log.error("role {} not found", roleName);
            throw new RoleNotFoundException("role " + roleName + " not found");
        } else {
            User user = optionalUser.get();
            Role role = optionalRole.get();
            user.getRoles().add(role);
            log.info("role {} added to user {}", roleName, username);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = null;
        if (optionalUser.isPresent()) {
            log.info("User {} is found", username);
            user = optionalUser.get();
        } else {
            log.error("User {} not found", username);
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(Role::getName)
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
