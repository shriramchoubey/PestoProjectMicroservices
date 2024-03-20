package com.example.pesto.auth.dao;

import com.example.pesto.auth.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;



@Entity
@Data
@Table(name="user")
@EqualsAndHashCode()
public class User{

    @Id
    @NotBlank(message = "username can't be blank")
    private String username;

    @NotBlank(message = "name can't be blank")
    private String name;
    private String email;
    @NotBlank(message = "User password can't be blank")
    private String password;

    public void setPassword(String password){
        this.password=password;
    }
    @Enumerated(EnumType.STRING)
    private Role role;

    public String getUsername(){
        return username;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
}
