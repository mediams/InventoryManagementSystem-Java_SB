package org.kariev.inventorymgtsystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "suppliers",
        indexes = {
                @Index(name = "idx_supplier_name_unique", columnList = "name")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Supplier {

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

    @NotBlank(message = "Contact Info is required")
    @Column(nullable = false, length = 254)
    @ToString.Include
    private String contactInfo;

    @NotBlank(message = "Address is required")
    @Column(nullable = false, length = 254)
    @ToString.Include
    private String address;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
