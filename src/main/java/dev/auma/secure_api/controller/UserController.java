package dev.auma.secure_api.controller;

import dev.auma.secure_api.exception.RoleNotFoundException;
import dev.auma.secure_api.exception.UserNotFoundException;
import dev.auma.secure_api.model.Role;
import dev.auma.secure_api.model.User;
import dev.auma.secure_api.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users")
@Hidden // We don't want to show these endpoints without security
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) throws UserNotFoundException {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/addRole/{username}/{roleName}")
    public ResponseEntity<?> addRoleToUser(@PathVariable String username, @PathVariable String roleName) throws UserNotFoundException, RoleNotFoundException {
        userService.addRoleToUser(username, roleName);
        return ResponseEntity.ok().build();
    }
}
