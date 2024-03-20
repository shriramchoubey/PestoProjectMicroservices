package com.example.pesto.enums;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum Role {

    ADMIN("ADMIN"),
    OPS("OPS"),
    USER("USER");

    @NotBlank(message =  "task status can't be blank") String Role;

}
