package com.example.pesto.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode()
public class UpdateOrderRequestDTO {

    @NotBlank
    @Size(min = 1, max=100)
    String id;

    @Pattern(regexp="^(ORDERED|DELIVERED|CANCELLED)$",message="this is not valid paymentStatus put either one of ORDERED | DELIVERED | CANCELLED")
    String status;

    @Pattern(regexp="^(PAID|UNPAID)$",message="this is not valid paymentStatus put either one of PAID or UNPAID")
    String paymentStatus;

}
