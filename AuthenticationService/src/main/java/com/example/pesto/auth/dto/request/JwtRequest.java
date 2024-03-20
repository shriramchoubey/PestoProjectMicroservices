package com.example.pesto.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.io.Serializable;

@Data
@EqualsAndHashCode()
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtRequest implements Serializable {


    @NotBlank
    @Size(min = 4, max=40)
    @JsonProperty("username")
    private String username;

    @NotBlank
    @Size(min = 1, max = 40)
    @JsonProperty("password")
    private String password;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}