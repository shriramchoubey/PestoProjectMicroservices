package com.example.pesto.gateway.dao;



import com.example.pesto.gateway.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode()
public class UserDTO {

    String id;
    String name;
    String username;
    String email;
    Role role;

}
