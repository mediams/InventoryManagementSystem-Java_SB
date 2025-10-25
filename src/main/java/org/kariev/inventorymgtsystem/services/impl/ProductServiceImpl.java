package org.kariev.inventorymgtsystem.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kariev.inventorymgtsystem.dtos.ProductDTO;
import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.kariev.inventorymgtsystem.exceptions.NotFoundException;
import org.kariev.inventorymgtsystem.mapper.ProductMapper;
import org.kariev.inventorymgtsystem.models.Category;
import org.kariev.inventorymgtsystem.models.Product;
import org.kariev.inventorymgtsystem.repositories.CategoryRepository;
import org.kariev.inventorymgtsystem.repositories.ProductRepository;
import org.kariev.inventorymgtsystem.services.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseDTO getProductById(UUID uuid) {

        Product product = repository.findById(uuid).orElseThrow(() -> new NotFoundException("Product not found"));

        return ResponseDTO.builder()
                .status(200)
                .message("Product found")
                .product(mapper.entityToDto(product))
                .build();

    }

    @Override
    public ResponseDTO getAllProducts() {

        List<Product> products = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<ProductDTO> dtos = mapper.entityToListDto(products);

        return ResponseDTO.builder()
                .status(200)
                .message("Success")
                .products(dtos)
                .build();
    }

    @Override
    public ResponseDTO createProduct(ProductDTO dto, MultipartFile imageFile) {

        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new NotFoundException("Category not found"));

        Product productToSave = mapper.dtoToEntity(dto);
        productToSave.setCategory(category);

        if (imageFile != null && !imageFile.isEmpty()) {
            log.info("Saving image to file {}", imageFile.getOriginalFilename());
            String imagePath = saveImage(imageFile);
            productToSave.setImageUrl(imagePath);
        }

        try {
            repository.save(productToSave);
            log.info("Product successfully saved: {}", productToSave);
        } catch (Exception e) {
            log.error("Error saving product to the database", e);
            throw new RuntimeException("Could not commit JPA transaction", e);
        }

        return ResponseDTO.builder()
                .status(200)
                .message("Product created")
                .build();
    }

    @Override
    public ResponseDTO updateProduct(ProductDTO dto, MultipartFile imageFile) {

        Product productToUpdate = repository.findById(dto.getId()).orElseThrow(() -> new NotFoundException("Product not found"));
        if (imageFile != null && !imageFile.isEmpty()) {
            String imagePath = saveImage(imageFile);
            productToUpdate.setImageUrl(imagePath);
        }
        if (dto.getCategoryId() != null && !dto.getCategoryId().toString().isEmpty()) {
            Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new NotFoundException("Category not found"));
            productToUpdate.setCategory(category);
        }
        if (dto.getName() != null && !dto.getName().isBlank()) {
            productToUpdate.setName(dto.getName());
        }
        if (dto.getSku() != null && !dto.getSku().isBlank()) {
            productToUpdate.setSku(dto.getSku());
        }
        if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
            productToUpdate.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null && dto.getPrice().compareTo(BigDecimal.ZERO) >= 0) {
            productToUpdate.setPrice(dto.getPrice());
        }
        if (dto.getStockQuantity() != null && dto.getStockQuantity() >= 0) {
            productToUpdate.setStockQuantity(dto.getStockQuantity());
        }

        repository.save(productToUpdate);

        return ResponseDTO.builder()
                .status(200)
                .message("Product updated")
                .build();

    }

    @Override
    public ResponseDTO deleteProduct(UUID uuid) {

        Product product = repository.findById(uuid).orElseThrow(() -> new NotFoundException("Product not found"));
        repository.delete(product);

        return ResponseDTO.builder()
                .status(200)
                .message("Product deleted")
                .build();
    }

    @Override
    public ResponseDTO searchProduct(String search) {

        List<Product> products = repository.findByNameContainingOrDescriptionContaining(search, search);
        if (products.isEmpty()) {
            throw new NotFoundException("Product not found");
        }

        List<ProductDTO> dtos = mapper.entityToListDto(products);

        return ResponseDTO.builder()
                .status(200)
                .message("Success")
                .products(dtos)
                .build();
    }

    private String saveImage(MultipartFile imageFile) {
        String contentType = imageFile.getContentType();
        log.info("Received file with content type: {}", contentType);  // Логируем тип контента файла

        if (!Objects.equals(contentType, "image/jpeg") &&
                !Objects.equals(contentType, "image/png") &&
                !Objects.equals(contentType, "image/gif") ||
                imageFile.getSize() > 1024 * 1024) {
            log.error("Invalid image type or size is too large (must be <= 1MB)");
            throw new IllegalArgumentException("Invalid image type");
        }

        // Создаём директорию для изображений в рабочем каталоге проекта или используем временную директорию
        Path dir = Paths.get(System.getProperty("user.home"), "images"); // Пример с пользовательской папкой
        File imageDir = dir.toFile();

        if (!imageDir.exists()) {
            boolean created = imageDir.mkdirs();
            if (created) {
                log.info("Image directory created successfully at {}", dir);
            } else {
                log.warn("Failed to create image directory at {}", dir);
            }
        } else {
            log.info("Image directory already exists at {}", dir);
        }

        String originalFileName = imageFile.getOriginalFilename();
        String safeFileName = Objects.requireNonNull(originalFileName, "File name cannot be null")
                .replaceAll("[^a-zA-Z0-9.-]", "_");
        log.info("Original file name: {}, Safe file name: {}", originalFileName, safeFileName);

        String uniqueFileName = UUID.randomUUID() + "_" + safeFileName;
        Path imageFilePath = dir.resolve(uniqueFileName);  // Сохраняем изображение в новой директории
        log.info("Saving image to path: {}", imageFilePath);

        try {
            imageFile.transferTo(imageFilePath.toFile());  // Сохраняем файл
            log.info("Image successfully saved to: {}", imageFilePath.toAbsolutePath());
        } catch (Exception e) {
            log.error("Error while saving image", e);  // Логируем ошибку с полным стеком исключений
            throw new IllegalArgumentException("Could not save image file", e);
        }

        return imageFilePath.toString();
    }

}
