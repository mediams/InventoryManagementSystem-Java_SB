package org.kariev.inventorymgtsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.kariev.inventorymgtsystem.dtos.ProductDTO;
import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.kariev.inventorymgtsystem.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getProductById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createProduct(
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("name") String name,
            @RequestParam("sku") String sku,
            @RequestParam("price") BigDecimal price,
            @RequestParam("stockQuantity") int stockQuantity,
            @RequestParam("categoryId") UUID categoryId,
            @RequestParam(value = "description", required = false) String description
    ) {
        ProductDTO dto = new ProductDTO();
        dto.setName(name);
        dto.setSku(sku);
        dto.setPrice(price);
        dto.setStockQuantity(stockQuantity);
        dto.setCategoryId(categoryId);
        dto.setDescription(description);

        return ResponseEntity.ok(service.createProduct(dto, imageFile));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateProduct(
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "sku", required = false) String sku,
            @RequestParam(value = "price", required = false) BigDecimal price,
            @RequestParam(value = "stockQuantity", required = false) int stockQuantity,
            @RequestParam(value = "categoryId", required = false) UUID categoryId,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("productId") UUID productId
    ) {
        ProductDTO dto = new ProductDTO();
        dto.setName(name);
        dto.setSku(sku);
        dto.setPrice(price);
        dto.setStockQuantity(stockQuantity);
        dto.setCategoryId(categoryId);
        dto.setDescription(description);
        dto.setId(productId);

        return ResponseEntity.ok(service.updateProduct(dto, imageFile));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable UUID id) {
        return ResponseEntity.ok(service.deleteProduct(id));
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> searchProduct(@RequestParam String input) {
        return ResponseEntity.ok(service.searchProduct(input));
    }
}
