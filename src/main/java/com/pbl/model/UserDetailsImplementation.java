package com.pbl.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImplementation implements UserDetails {
    private UserAuth auth;

    public UserDetailsImplementation(UserAuth auth) {
        this.auth=auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+this.auth.getRole().name()));
    }

    @Override
    public String getPassword() {
        return this.auth.getPassword();
    }

    @Override
    public String getUsername() {
        return this.auth.getUsername();
    }
}
