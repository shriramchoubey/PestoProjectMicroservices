package com.example.pesto.enums;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum PaymentStatus {
    PAID("PAID"),
    UNPAID("UNPAID");
    @NotBlank(message =  "payment status can't be blank") String PaymentStatus;
}
