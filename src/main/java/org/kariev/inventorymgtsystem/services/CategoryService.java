package org.kariev.inventorymgtsystem.services;

import org.kariev.inventorymgtsystem.dtos.CategoryDTO;
import org.kariev.inventorymgtsystem.dtos.ResponseDTO;

import java.util.UUID;

public interface CategoryService {

    ResponseDTO createCategory(CategoryDTO dto);

    ResponseDTO updateCategoryById(UUID id, CategoryDTO dto);

    ResponseDTO deleteCategoryById(UUID id);

    ResponseDTO getCategoryById(UUID id);

    ResponseDTO getAllCategories();

}
