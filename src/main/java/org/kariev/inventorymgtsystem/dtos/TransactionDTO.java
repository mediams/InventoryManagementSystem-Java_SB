package org.kariev.inventorymgtsystem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kariev.inventorymgtsystem.enums.TransactionStatus;
import org.kariev.inventorymgtsystem.enums.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO {

    @NotNull
    private UUID id;

    private TransactionType transactionType;

    @NotNull(message = "Status is required")
    private TransactionStatus status;

    @NotNull(message = "Total price is required")
    @PositiveOrZero(message = "Total price must be ≥ 0")
    @Digits(integer = 14, fraction = 2, message = "Total price must have up to 14 digits and 2 decimals")
    private BigDecimal totalPrice;

    @NotNull(message = "Total products is required")
    @PositiveOrZero(message = "Total products must be ≥ 0")
    private Integer totalProducts;

    @Size(max = 2000, message = "Description must be ≤ 2000 characters")
    private String description;

    @Size(max = 2000, message = "Note must be ≤ 2000 characters")
    private String note;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant updatedAt;

    @NotNull
    private UUID supplierId;

    private UserDTO user;
    private SupplierDTO supplier;
    private ProductDTO product;

    @NotNull
    @Size(min = 1, message = "At least one item is required")
    @Valid
    private List<TransactionItemDTO> items;

    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be 3-letter ISO code (A–Z)")
    private String currency;
}
