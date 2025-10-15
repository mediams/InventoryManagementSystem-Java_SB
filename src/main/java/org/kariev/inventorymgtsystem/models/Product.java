package org.kariev.inventorymgtsystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "products",
        indexes = {
                @Index(name = "idx_products_unique", columnList = "sku", unique = true)
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false, length = 120)
    @ToString.Include
    private String name;

    @NotBlank(message = "SKU is required")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "SKU may contain A-Z, 0-9 and '-'")
    @Column(nullable = false, unique = true, length = 32)
    @ToString.Include
    private String sku;

    @NotNull(message = "Price is required")
    @Positive(message = "Product price must be a positive value")
    @Column(nullable = false, precision = 19, scale = 2)
    @ToString.Include
    private BigDecimal price;

    @NotNull
    @Min(value = 0, message = "Stock quantity cannot be negative")
    @Column(nullable = false)
    @ToString.Include
    private Integer stockQuantity;

    @NotBlank(message = "Description is required")
    @Column(nullable = false, length = 254)
    @ToString.Include
    private String description;

    @Column(columnDefinition = "timestamp")
    private LocalDateTime expiryDate;

    @Column(length = 254)
    @ToString.Include
    private String imageUrl;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
