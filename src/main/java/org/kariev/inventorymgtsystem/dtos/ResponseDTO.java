package org.kariev.inventorymgtsystem.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {

    private int status;
    private String message;

    private T data;

    private String token;
    private String expirationTime;

    private final Instant timestamp = Instant.now();
}
