package org.kariev.inventorymgtsystem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionItemDTO {

    @NotNull(message = "productId is required")
    private UUID productId;

    @NotNull(message = "quantity is required")
    @Positive(message = "quantity must be > 0")
    private Integer quantity;

    @NotNull(message = "unitPrice is required")
    @PositiveOrZero(message = "unitPrice must be ≥ 0")
    @Digits(integer = 14, fraction = 2, message = "unitPrice must have up to 14 digits and 2 decimals")
    private BigDecimal unitPrice;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal lineTotal;
}
