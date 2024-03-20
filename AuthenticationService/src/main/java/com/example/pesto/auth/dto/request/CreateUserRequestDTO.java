package com.example.pesto.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode()
public class CreateUserRequestDTO {
    @NotBlank(message = "username can't be blank")
    private String username;

    @NotBlank(message = "name can't be blank")
    @Size(min = 4, max=40)
    private String name;

    private String email;

    @NotBlank(message = "User password can't be blank")
    @Size(min = 4, max=40)
    private String password;

    @Pattern(regexp="^(ADMIN|OPS|USER)$",message="this is not valid paymentStatus put either one of ADMIN | OPS | USER")
    private String role;
}
