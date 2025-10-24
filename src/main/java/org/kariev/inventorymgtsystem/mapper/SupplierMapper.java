package org.kariev.inventorymgtsystem.mapper;

import org.kariev.inventorymgtsystem.dtos.SupplierDTO;
import org.kariev.inventorymgtsystem.models.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface SupplierMapper {
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Supplier dtoToEntity(SupplierDTO dto);

    Supplier entityToDto(Supplier supplier);

    List<SupplierDTO> entityListToDto(List<Supplier> suppliers);
}
