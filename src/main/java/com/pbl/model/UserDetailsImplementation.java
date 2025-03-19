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
        return List.of(new SimpleGrantedAuthority(this.auth.getRole().name()));
    }

    @Override
    public String getPassword() {
        return this.auth.getPassword();
    }

    /**w
     * Returns the username used to authenticate the user. Cannot return
     * <code>null</code>.
     *
     * @return the username (never <code>null</code>)
     */
    @Override
    public String getUsername() {
        return this.auth.getUsername();
    }
}
