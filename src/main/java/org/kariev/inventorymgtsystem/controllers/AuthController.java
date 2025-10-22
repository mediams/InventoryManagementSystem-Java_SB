package org.kariev.inventorymgtsystem.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kariev.inventorymgtsystem.dtos.LoginRequestDTO;
import org.kariev.inventorymgtsystem.dtos.RegisterRequestDTO;
import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.kariev.inventorymgtsystem.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request) {
        return ResponseEntity.ok(service.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody @Valid LoginRequestDTO request) {
        return ResponseEntity.ok(service.loginUser(request));
    }

}
