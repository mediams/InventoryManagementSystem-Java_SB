package org.kariev.inventorymgtsystem.services;

import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.kariev.inventorymgtsystem.dtos.TransactionRequestDTO;
import org.kariev.inventorymgtsystem.enums.TransactionStatus;

import java.util.UUID;

public interface TransactionService {
    ResponseDTO purchaseTransaction(TransactionRequestDTO transactionRequestDTO);

    ResponseDTO sellTransaction(TransactionRequestDTO transactionRequestDTO);

    ResponseDTO returnToSupplierTransaction(TransactionRequestDTO transactionRequestDTO);

    ResponseDTO getAllTransactions(int page, int size, String filter);

    ResponseDTO getTransactionById(UUID id);

    ResponseDTO getAllTransactionsByMonthAndYear(int month, int year);

    ResponseDTO updateTransaction(UUID transactionId, TransactionStatus transactionStatus);
}
