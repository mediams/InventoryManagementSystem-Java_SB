package org.kariev.inventorymgtsystem.mapper;

import org.kariev.inventorymgtsystem.dtos.UserDTO;
import org.kariev.inventorymgtsystem.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TransactionMapper.class})
public interface UserMapper {

    UserDTO entityToDto(User entity);

    List<UserDTO> entityListToDto(List<User> entities);

}
