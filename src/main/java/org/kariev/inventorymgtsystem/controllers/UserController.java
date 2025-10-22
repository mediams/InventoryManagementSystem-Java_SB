package org.kariev.inventorymgtsystem.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.kariev.inventorymgtsystem.dtos.UserDTO;
import org.kariev.inventorymgtsystem.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseDTO> getUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or #id.toString() == authentication.principal.id")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or #id.toString() == authentication.principal.id")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable UUID id, @Valid @RequestBody UserDTO user) {
        return ResponseEntity.ok(service.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or #id.toString() == authentication.principal.id")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/transactions")
    @PreAuthorize("hasAuthority('ADMIN') or #id.toString() == authentication.principal.id")
    public ResponseEntity<ResponseDTO> getUserTransactions(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getUserTransactions(id));
    }
}
