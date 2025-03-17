package com.pbl.controller;

import com.pbl.model.ROLE;
import com.pbl.model.UserAuth;
import com.pbl.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserAuthService service;

    @GetMapping("/")
    public String prn() {
        return "Hello Authentication Controller here...!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuth userAuth) {
        return ResponseEntity.status(HttpStatus.OK).body("In Progress!!!");
    }

    @PostMapping("/register")
    public  ResponseEntity<UserAuth> register(@RequestBody UserAuth userAuth) {
        userAuth.setRole(ROLE.ADMIN);
        UserAuth register;
        try {
            register = this.service.register(userAuth);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(register);
    }
}