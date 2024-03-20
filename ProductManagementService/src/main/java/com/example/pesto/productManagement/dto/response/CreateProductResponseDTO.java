package com.example.pesto.productManagement.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode()
public class CreateProductResponseDTO{
    private String message;
    private String id;
}
