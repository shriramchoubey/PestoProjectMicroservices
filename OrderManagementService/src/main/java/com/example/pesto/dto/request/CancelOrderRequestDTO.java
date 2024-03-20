package com.example.pesto.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Data
@EqualsAndHashCode()
public class CancelOrderRequestDTO {
    @NotBlank
    @Size(min = 1, max=100)
    private String id;
}
