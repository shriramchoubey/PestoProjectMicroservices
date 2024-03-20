package com.example.pesto.productManagement.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode()
public class DeleteProductResponseDTO {
    private String message;

}