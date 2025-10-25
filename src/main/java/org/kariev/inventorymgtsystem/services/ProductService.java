package org.kariev.inventorymgtsystem.services;

import org.kariev.inventorymgtsystem.dtos.ProductDTO;
import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ProductService {
    ResponseDTO getProductById(UUID uuid);

    ResponseDTO getAllProducts();

    ResponseDTO createProduct(ProductDTO dto, MultipartFile file);

    ResponseDTO updateProduct(ProductDTO dto, MultipartFile imageFile);

    ResponseDTO deleteProduct(UUID uuid);

    ResponseDTO searchProduct(String search);
}
