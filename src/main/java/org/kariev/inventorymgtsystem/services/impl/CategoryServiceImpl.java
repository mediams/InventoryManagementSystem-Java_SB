package org.kariev.inventorymgtsystem.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kariev.inventorymgtsystem.dtos.CategoryDTO;
import org.kariev.inventorymgtsystem.dtos.ResponseDTO;
import org.kariev.inventorymgtsystem.exceptions.NotFoundException;
import org.kariev.inventorymgtsystem.mapper.CategoryMapper;
import org.kariev.inventorymgtsystem.models.Category;
import org.kariev.inventorymgtsystem.repositories.CategoryRepository;
import org.kariev.inventorymgtsystem.services.CategoryService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public ResponseDTO createCategory(CategoryDTO dto) {

        Category category = mapper.dtoToEntity(dto);
        mapper.entityToDto(repository.save(category));
        return ResponseDTO.builder()
                .status(200)
                .message("Category created")
                .build();
    }

    @Override
    public ResponseDTO updateCategoryById(UUID id, CategoryDTO dto) {
        Category existingCategory = repository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
        existingCategory.setName(dto.getName());

        repository.save(existingCategory);

        return ResponseDTO.builder()
                .status(200)
                .message("Category found")
                .category(dto)
                .build();
    }

    @Override
    public ResponseDTO deleteCategoryById(UUID id) {
        repository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
        repository.deleteById(id);
        return ResponseDTO.builder()
                .status(200)
                .message("Category deleted")
                .build();
    }

    @Override
    public ResponseDTO getCategoryById(UUID id) {
        Category category = repository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
        CategoryDTO dto = mapper.entityToDto(category);
        return ResponseDTO.builder()
                .status(200)
                .message("Category found")
                .category(dto)
                .build();
    }

    @Override
    public ResponseDTO getAllCategories() {
        List<Category> categories = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        categories.forEach(category -> category.setProducts(null));
        List<CategoryDTO> dtos = mapper.entityListToDto(categories);
        return ResponseDTO.builder()
                .status(200)
                .message("Success")
                .categories(dtos)
                .build();
    }
}
