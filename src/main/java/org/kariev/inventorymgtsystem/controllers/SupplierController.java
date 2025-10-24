package org.kariev.inventorymgtsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.kariev.inventorymgtsystem.dtos.SupplierDTO;
import org.kariev.inventorymgtsystem.services.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getAllSuppliers() {
        return ResponseEntity.ok(service.getAllSupplier());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getSupplierById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getSupplierById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createSupplier(@RequestBody SupplierDTO dto) {
        return ResponseEntity.ok(service.createSupplier(dto));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateSupplier(@RequestBody SupplierDTO dto, @PathVariable UUID id) {
        return ResponseEntity.ok(service.updateSupplierById(id, dto));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteSupplier(@PathVariable UUID id) {
        return ResponseEntity.ok(service.deleteSupplierById(id));
    }
}
