package org.kariev.inventorymgtsystem.mapper;

import org.kariev.inventorymgtsystem.dtos.CategoryDTO;
import org.kariev.inventorymgtsystem.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {TransactionMapper.class},
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface CategoryMapper {
    @Mapping(target = "creationDate", ignore = true)
    Category dtoToEntity(CategoryDTO dto);

    CategoryDTO entityToDto(Category category);

    List<CategoryDTO> entityListToDto(List<Category> categories);
}
