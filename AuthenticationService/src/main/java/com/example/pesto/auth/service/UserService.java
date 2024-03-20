package com.example.pesto.auth.service;
import com.example.pesto.auth.dto.request.CreateUserRequestDTO;
import com.example.pesto.auth.dao.User;
import com.example.pesto.auth.enums.Role;
import com.example.pesto.auth.exceptions.ProjectUserAlreadyExist;
import com.example.pesto.auth.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService{

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> addUser(CreateUserRequestDTO requestDTO){

        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setName(requestDTO.getName());
        user.setRole(Role.valueOf(requestDTO.getRole()));
        user.setEmail(requestDTO.getEmail());


        user.setPassword(bcryptEncoder.encode(requestDTO.getPassword()));

        User existingUser = userRepository.findById(requestDTO.getUsername()).orElse(null);
        if(existingUser != null){
            log.info("user already exist in DB");
            String message = "{\"message\": \"User already exist in DB\"}";
            return ResponseEntity.badRequest().body(message);
        }
        userRepository.save(user);
        String message = "{\"message\": \"Successfully registered User\"}";
        log.info("saved new user to db");
        return ResponseEntity.ok(message);
    }
}