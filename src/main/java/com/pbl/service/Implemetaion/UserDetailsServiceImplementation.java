package com.pbl.service.Implemetaion;

import com.pbl.model.UserAuth;
import com.pbl.model.UserDetailsImplementation;
import com.pbl.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    @Autowired
    UserAuthRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth = repo.findByUsername(username);
        if(userAuth==null) {
            throw  new UsernameNotFoundException("Username not found!");
        }
        return new UserDetailsImplementation(userAuth);
    }
}