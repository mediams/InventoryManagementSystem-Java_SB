package org.kariev.inventorymgtsystem.mapper;

import org.kariev.inventorymgtsystem.dtos.UserDTO;
import org.kariev.inventorymgtsystem.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {TransactionMapper.class},
        unmappedTargetPolicy = ReportingPolicy.WARN
)
public interface UserMapper {

    @Mapping(target = "transactionIds", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    UserDTO entityToDto(User entity);
    List<UserDTO> entityListToDto(List<User> entities);
}
