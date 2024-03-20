package com.example.pesto.auth.service;

import java.util.ArrayList;
import java.util.Objects;


import com.example.pesto.auth.util.CustomUser;
import com.example.pesto.auth.dao.User;
import com.example.pesto.auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class JwtUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            CustomUser user = getUser(username);
            log.info("Called for User Details");
            return user;

        }catch (UsernameNotFoundException exception){
            throw new UsernameNotFoundException("Invalid Access Token ");
        }
    }

    private CustomUser getUser(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElse(null);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUser(user.getUsername(), user.getName(), user.getEmail(), user.getRole(), user.getUsername(), user.getPassword(), new ArrayList<>());
    }

}
