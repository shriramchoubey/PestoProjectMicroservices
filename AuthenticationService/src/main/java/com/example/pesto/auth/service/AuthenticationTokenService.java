package com.example.pesto.auth.service;

import com.example.pesto.auth.dto.response.JwtResponse;
import com.example.pesto.auth.util.JwtTokenUtil;
import com.example.pesto.auth.exceptions.UserDisabledException;
import com.example.pesto.auth.exceptions.WrongLoginCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationTokenService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserService userDetailsService;

    public JwtResponse createToken(String username, String password){
        authenticate(username, password);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.GenerateToken(userDetails.getUsername());
        return new JwtResponse(token);
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new UserDisabledException(e);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            throw new WrongLoginCredentials(e);
        }
    }
}
