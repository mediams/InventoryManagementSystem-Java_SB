package org.kariev.inventorymgtsystem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authentication", description = "Authentication and registration endpoints")
public class AuthController {
    private final UserService service;

    @PostMapping("/register")
    @Operation(summary = "Register user", description = "Creates a new user and returns the authentication token")
    @ApiResponse(responseCode = "200", description = "User registered successfully",
            content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    public ResponseEntity<ResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request) {
        return ResponseEntity.ok(service.registerUser(request));
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticates a user and returns a JWT token")
    @ApiResponse(responseCode = "200", description = "Login successful",
            content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody @Valid LoginRequestDTO request) {
        return ResponseEntity.ok(service.loginUser(request));
    }

}
