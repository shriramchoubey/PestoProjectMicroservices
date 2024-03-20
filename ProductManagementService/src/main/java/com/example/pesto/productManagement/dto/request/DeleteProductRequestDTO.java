package com.example.pesto.productManagement.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@EqualsAndHashCode()
public class DeleteProductRequestDTO{
    @NotBlank
    @Size(min = 1, max=100)
    private String id;
}
