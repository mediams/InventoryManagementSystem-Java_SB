package org.kariev.inventorymgtsystem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.kariev.inventorymgtsystem.enums.UserRole;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotBlank(message = "Name is required")
    @Size(max = 154, message = "Name must be ≤ 154 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 254, message = "Email must be ≤ 254 characters")
    private String email;

    @Size(max = 20, message = "Phone must be ≤ 20 characters")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone must be E.164")
    private String phoneNumber;

    private UserRole role;

    private List<TransactionDTO> transactions;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private List<UUID> transactionIds;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Instant createdAt;

    @Override
    public String toString() {
        return "UserDTO(id=" + id + ", name=" + name + ", role=" + role + ")";
    }
}
