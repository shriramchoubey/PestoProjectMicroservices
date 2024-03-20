package com.example.pesto.enums;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum OrderStatus {
    ORDERED("ORDERED"),
    DELIVERED("DELIVERED"),
    CANCELLED("CANCELLED");

    @NotBlank(message =  "order status can't be blank") String OrderStatus;
}

