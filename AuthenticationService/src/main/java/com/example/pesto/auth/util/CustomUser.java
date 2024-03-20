package com.example.pesto.auth.util;

import com.example.pesto.auth.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomUser extends User {

    String id;
    String username;
    String name;
    String email;
    Role role;

    public CustomUser(String id, String name, String email, Role role, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public String getId(){
        return id;
    }
    public Role getRole(){
        return role;
    }

    public String getUsername(){
        return username;
    }

}
