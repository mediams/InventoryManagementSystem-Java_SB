package org.kariev.inventorymgtsystem.mapper;

import org.kariev.inventorymgtsystem.dtos.SupplierDTO;
import org.kariev.inventorymgtsystem.models.Supplier;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SupplierMapper {

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Supplier dtoToEntity(SupplierDTO dto);

    SupplierDTO entityToDto(Supplier supplier);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(SupplierDTO dto, @MappingTarget Supplier entity);

    List<SupplierDTO> entityListToDto(List<Supplier> suppliers);
}
