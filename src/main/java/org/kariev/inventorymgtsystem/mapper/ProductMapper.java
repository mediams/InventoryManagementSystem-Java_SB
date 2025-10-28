package org.kariev.inventorymgtsystem.mapper;

import org.kariev.inventorymgtsystem.dtos.ProductDTO;
import org.kariev.inventorymgtsystem.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ProductMapper {
    @Mapping(target = "creationDate", ignore = true)
    Product dtoToEntity(ProductDTO dto);

    List<ProductDTO> entityToListDto(List<Product> products);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "createdAt", source = "creationDate")
    @Mapping(target = "supplierId", ignore = true)
    @Mapping(target = "internalCode", ignore = true)
    ProductDTO entityToDto(Product product);

}
