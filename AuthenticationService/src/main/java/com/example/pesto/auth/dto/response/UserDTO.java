package com.example.pesto.auth.dto.response;


import com.example.pesto.auth.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode()
public class UserDTO {

    String id;
    String name;
    String email;
    Role role;
    String message;
}
