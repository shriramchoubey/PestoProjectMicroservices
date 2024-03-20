package com.example.pesto.auth.controller;

import com.example.pesto.auth.dto.request.JwtRequest;
import com.example.pesto.auth.dto.response.UserDTO;
import com.example.pesto.auth.repository.UserRepository;
import com.example.pesto.auth.service.AuthenticationTokenService;
import com.example.pesto.auth.service.JwtUserService;
import com.example.pesto.auth.util.CustomUser;
import com.example.pesto.auth.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping(Endpoints.Auth.BASE_URL)
public class JwtAuthenticationController {
    @Autowired
    AuthenticationTokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    JwtUserService jwtUserService;

    @PostMapping(value = Endpoints.Auth.AUTHENTICATE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid JwtRequest reequest) throws Exception {
        return ResponseEntity.ok(tokenService.createToken(reequest.getUsername(), reequest.getPassword()));
    }

    @GetMapping(value = Endpoints.Auth.VALIDATE_TOKEN)
    public ResponseEntity<?> validateToken(@RequestParam("token") String token) throws Exception {
        try {
            String username = jwtTokenUtil.extractUsername(token);
            CustomUser userDetails = this.jwtUserService.loadUserByUsername(username);
            Boolean isValidToken = jwtTokenUtil.validateToken(token, userDetails);

            if(isValidToken){
                UserDTO userDTO = UserDTO.builder()
                        .id(userDetails.getId())
                        .name(userDetails.getName())
                        .email(userDetails.getEmail())
                        .role(userDetails.getRole())
                        .message("Token is valid")
                        .build();
                return ResponseEntity.ok(userDTO);
            }
        } catch (IllegalArgumentException e) {
            log.info("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            log.info("JWT Token has expired");
        }
        UserDTO userDTO = UserDTO.builder().message("Token is not valid").build();
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(userDTO);
    }
}