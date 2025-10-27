package org.kariev.inventorymgtsystem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplierDTO {

    private UUID id;

    @NotBlank(message = "Name is required")
    @Size(max = 154, message = "Name must be ≤ 154 characters")
    private String name;

    @NotBlank(message = "Contact info is required")
    @Size(max = 254, message = "Contact info must be ≤ 254 characters")
    private String contactInfo;

    @NotBlank(message = "Address is required")
    @Size(max = 254, message = "Address must be ≤ 254 characters")
    private String address;

}
