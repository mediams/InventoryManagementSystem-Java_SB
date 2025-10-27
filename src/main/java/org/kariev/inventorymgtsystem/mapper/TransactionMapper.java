package org.kariev.inventorymgtsystem.mapper;

import org.kariev.inventorymgtsystem.dtos.TransactionDTO;
import org.kariev.inventorymgtsystem.models.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mappings({
            @Mapping(target = "supplier", ignore = true),
            @Mapping(target = "items", ignore = true),
            @Mapping(target = "currency", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "supplierId", ignore = true)
    })
    TransactionDTO entityToDto(Transaction entity);


    List<TransactionDTO> entityToListDto(List<Transaction> transactions);

}
