package org.kariev.inventorymgtsystem.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    private UUID id;

    @NotNull(message = "categoryId is required")
    private UUID categoryId;

    @Size(max = 64, message = "Internal code must be ≤ 64 characters")
    private String internalCode;

    private UUID supplierId;

    @NotBlank(message = "Name is required")
    @Size(max = 154, message = "Name must be ≤ 154 characters")
    private String name;

    @Size(max = 64, message = "SKU must be ≤ 64 characters")
    @Pattern(regexp = "^[A-Z0-9-]{3,32}$", message = "SKU format is invalid")
    private String sku;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be ≥ 0")
    @Digits(integer = 12, fraction = 2, message = "Price must have up to 12 digits and 2 decimals")
    private BigDecimal price;

    @NotNull(message = "Stock quantity is required")
    @PositiveOrZero(message = "Stock quantity must be ≥ 0")
    private Integer stockQuantity;

    @Size(max = 2000, message = "Description must be ≤ 2000 characters")
    private String description;

    private LocalDate expiryDate;

    @Size(max = 512, message = "Image URL must be ≤ 512 characters")
    private String imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant createdAt;
}
