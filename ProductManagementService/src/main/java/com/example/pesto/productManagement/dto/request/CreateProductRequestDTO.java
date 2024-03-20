package com.example.pesto.productManagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode()
public class CreateProductRequestDTO {
    @NotBlank
    @Size(min = 1, max=100)
    private String name;
    @NotBlank
    @Size(min = 1, max=500)
    private String description;
    @NotBlank
    @Size(min = 1, max=100)
    private String image;
}
