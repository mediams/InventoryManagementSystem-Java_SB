package org.kariev.inventorymgtsystem.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kariev.inventorymgtsystem.dtos.LoginRequestDTO;
import org.kariev.inventorymgtsystem.dtos.RegisterRequestDTO;
import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.kariev.inventorymgtsystem.dtos.UserDTO;
import org.kariev.inventorymgtsystem.enums.UserRole;
import org.kariev.inventorymgtsystem.exceptions.NotFoundException;
import org.kariev.inventorymgtsystem.mapper.UserMapper;
import org.kariev.inventorymgtsystem.models.User;
import org.kariev.inventorymgtsystem.repositories.UserRepository;
import org.kariev.inventorymgtsystem.security.JwtUtils;
import org.kariev.inventorymgtsystem.services.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    private final JwtUtils jwtUtils;

    @Override
    public ResponseDTO registerUser(RegisterRequestDTO dto) {
        UserRole userRole = UserRole.MANAGER;
        if (dto.getRole() != null) {
            userRole = dto.getRole();
        }

        User userToSave = User.builder()
                .phoneNumber(dto.getPhoneNumber())
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(userRole)
                .build();
        repository.save(userToSave);

        return ResponseDTO.builder()
                .status(HttpStatus.CREATED.value())
                .message("User was successfully registered")
                .build();
    }


    @Override
    public ResponseDTO loginUser(LoginRequestDTO dto) {

        User user = repository.findByEmail(dto.getEmail()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        String token = jwtUtils.generateToken(user.getEmail());

        return ResponseDTO.builder()
                .status(200)
                .message("User Logged in Successfully")
                .role(user.getRole())
                .token(token)
                .expirationTime("6 months")
                .build();
    }

    @Override
    public ResponseDTO getAllUsers() {
        List<User> users = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        users.forEach(user -> user.setTransactions(null));
        List<UserDTO> userDTOS = mapper.entityListToDto(users);

        return ResponseDTO.builder()
                .status(200)
                .message("All users successfully retrieved")
                .users(userDTOS)
                .build();
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = repository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setTransactions(null);
        return user;
    }

    @Override
    public ResponseDTO getUserById(UUID id) {
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        UserDTO userDTO = mapper.entityToDto(user);
        userDTO.setTransactionIds(null);

        return ResponseDTO.builder()
                .status(200)
                .message("User successfully retrieved")
                .user(userDTO)
                .build();

    }

    @Override
    public ResponseDTO getUserByEmail(String email) {
        return null;
    }

    @Override
    public ResponseDTO updateUser(UUID id, UserDTO dto) {

        User existingUser = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        if (dto.getEmail() != null) existingUser.setEmail(dto.getEmail());
//        if (dto.getPhoneNumber() != null) existingUser.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getName() != null) existingUser.setName(dto.getName());
        if (dto.getRole() != null) existingUser.setRole(dto.getRole());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty())
            existingUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        repository.save(existingUser);

        return ResponseDTO.builder()
                .status(200)
                .message("User successfully updated")
                .build();
    }

    @Override
    public ResponseDTO deleteUser(UUID id) {
        repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        repository.deleteById(id);
        return ResponseDTO.builder()
                .status(200)
                .message("User successfully updated")
                .build();
    }

    @Override
    public ResponseDTO getUserTransactions(UUID id) {

        User user = repository.findById(id).orElseThrow(() -> new NotFoundException("User Not Found"));

        UserDTO userDTO = mapper.entityToDto(user);

        userDTO.getTransactions().forEach(transactionDTO -> {
            transactionDTO.setUser(null);
            transactionDTO.setSupplier(null);
        });

        return ResponseDTO.builder()
                .status(200)
                .message("Successfully retrieved user transactions")
                .user(userDTO)
                .build();
    }


}
