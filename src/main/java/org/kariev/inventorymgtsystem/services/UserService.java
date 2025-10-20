package org.kariev.inventorymgtsystem.services;

import org.kariev.inventorymgtsystem.dtos.LoginRequestDTO;
import org.kariev.inventorymgtsystem.dtos.RegisterRequestDTO;
import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.kariev.inventorymgtsystem.dtos.UserDTO;
import org.kariev.inventorymgtsystem.models.User;

import java.util.UUID;

public interface UserService {
    ResponseDTO registerUser(RegisterRequestDTO dto);

    ResponseDTO loginUser(LoginRequestDTO dto);

    ResponseDTO getAllUsers();

    User getCurrentUser();

    ResponseDTO getUserById(UUID id);

    ResponseDTO getUserByEmail(String email);

    ResponseDTO updateUser(UUID id, UserDTO dto);

    ResponseDTO deleteUser(UUID id);

    ResponseDTO getUserTransactions(UUID id);
}
