package com.example.pesto.productManagement.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@EqualsAndHashCode()
public class UpdateProductRequestDTO {

    @NotBlank(message = "id should not be empty or null")
    @Size(min = 1, max=100)
    private String id;

    private String name;
    private String description;
    private String image;
}
