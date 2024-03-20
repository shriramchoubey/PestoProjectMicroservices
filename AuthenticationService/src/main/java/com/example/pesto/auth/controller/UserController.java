package com.example.pesto.auth.controller;

import com.example.pesto.auth.dto.request.CreateUserRequestDTO;
import com.example.pesto.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(Endpoints.User.BASE_URL)
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = Endpoints.User.CREATE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid CreateUserRequestDTO requestDTO) throws Exception {
        return userService.addUser(requestDTO);
    }


}