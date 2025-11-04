package org.kariev.inventorymgtsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.kariev.inventorymgtsystem.dtos.CategoryDTO;
import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.kariev.inventorymgtsystem.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> create(@RequestBody final CategoryDTO dto) {
        return ResponseEntity.ok(service.createCategory(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable final UUID id) {
        ResponseDTO categoryById = service.getCategoryById(id);
        return categoryById != null ? ResponseEntity.ok(categoryById) : ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> update(@RequestBody final CategoryDTO dto, @PathVariable final UUID id) {
        return ResponseEntity.ok(service.updateCategoryById(id, dto));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> delete(@PathVariable final UUID id) {
        return ResponseEntity.ok(service.deleteCategoryById(id));
    }

}
