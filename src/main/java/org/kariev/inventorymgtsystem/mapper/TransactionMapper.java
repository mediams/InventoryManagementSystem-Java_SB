package org.kariev.inventorymgtsystem.mapper;

import org.kariev.inventorymgtsystem.dtos.TransactionDTO;
import org.kariev.inventorymgtsystem.models.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDTO entityToDto(Transaction entity);
}
