package org.kariev.inventorymgtsystem.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.kariev.inventorymgtsystem.enums.UserRole;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    private int status;
    private String message;

    private String token;
    private UserRole role;
    private String expirationTime;

    private Integer totalPages;
    private Long totalElements;

    private UserDTO user;
    private List<UserDTO> users;

    private SupplierDTO supplier;
    private List<SupplierDTO> suppliers;

    private CategoryDTO category;
    private List<CategoryDTO> categories;

    private ProductDTO product;
    private List<ProductDTO> products;

    private TransactionDTO transaction;
    private List<TransactionDTO> transactions;

    private final LocalDateTime timestamp = LocalDateTime.now();

}
