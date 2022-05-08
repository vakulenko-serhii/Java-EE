package com.example.demo.model.security;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
@ToString(callSuper = true)
public class AuthenticatedUser extends User {

    public AuthenticatedUser(
            final String username,
            final String password,
            final List<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
    }
}
