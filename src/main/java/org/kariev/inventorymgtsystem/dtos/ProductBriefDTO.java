package org.kariev.inventorymgtsystem.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBriefDTO {

    private UUID id;

    @NotBlank(message = "Name is required")
    @Size(max = 154, message = "Name must be ≤ 154 characters")
    private String name;
}
