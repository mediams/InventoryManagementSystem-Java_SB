package org.kariev.inventorymgtsystem.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.kariev.inventorymgtsystem.dtos.TransactionRequestDTO;
import org.kariev.inventorymgtsystem.dtos.UpdateTransactionStatusRequest;
import org.kariev.inventorymgtsystem.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @PostMapping("/purchase")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> purchase(@RequestBody @Valid TransactionRequestDTO request) {
        return ResponseEntity.ok(service.purchaseTransaction(request));
    }


    @PostMapping("/sell")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> sell(@RequestBody @Valid TransactionRequestDTO request) {
        return ResponseEntity.ok(service.sellTransaction(request));
    }

    @PostMapping("/return")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> returnTransaction(@RequestBody @Valid TransactionRequestDTO request) {
        return ResponseEntity.ok(service.returnToSupplierTransaction(request));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getAllTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size,
            @RequestParam(required = false) String filter) {

        System.out.println("SEARCH VALUE IS: " +filter);
        return ResponseEntity.ok(service.getAllTransactions(page, size, filter));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getTransactionById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getTransactionById(id));
    }

    @GetMapping("/by-month-year")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getAllTransactionsByMonthAndYear(
            @RequestParam int month,
            @RequestParam int year
    ) {
        return ResponseEntity.ok(service.getAllTransactionsByMonthAndYear(month, year));
    }

    @PutMapping("/transactions/{transactionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateTransaction(
            @PathVariable UUID transactionId,
            @RequestBody UpdateTransactionStatusRequest req
    ) {
        return ResponseEntity.ok(service.updateTransaction(transactionId, req.transactionStatus()));
    }
}
