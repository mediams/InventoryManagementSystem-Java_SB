package org.kariev.inventorymgtsystem.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductBriefDTO {

    private UUID id;

    @NotBlank(message = "Name is required")
    @Size(max = 154, message = "Name must be ≤ 154 characters")
    private String name;
}
