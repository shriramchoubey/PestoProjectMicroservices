package com.example.pesto.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode()
public class CancelOrderResponseDTO {
    private String message;
    private String id;

}