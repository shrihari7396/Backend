package com.pbl.controller;

import com.pbl.helper.controller.LoginResponse;
import com.pbl.model.Admin;
import com.pbl.model.ROLE;
import com.pbl.model.Student;
import com.pbl.model.UserAuth;
import com.pbl.service.AdminService;
import com.pbl.service.StudentService;
import com.pbl.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AdminService adminService;

    @Autowired
    StudentService studentService;


    @Autowired
    private UserAuthService service;

    @GetMapping("/")
    public String prn() {
        return "Hello Authentication Controller here...!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuth userAuth) {
        LoginResponse response = new LoginResponse();
        response.setUsername(userAuth.getUsername());
        response.setToken(service.verify(userAuth));
        if(userAuth.getRole().equals(ROLE.ADMIN)) {
            Admin adminByUsername = adminService.getAdminByUsername(userAuth.getUsername());
            response.setName(adminByUsername.getName());
        } else {
            Student studentByUsername = studentService.getStudentByUsername(userAuth.getUsername());
            response.setName(studentByUsername.getName());
        }
        response.setRole(userAuth.getRole().toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/register")
    public  ResponseEntity<?> register(@RequestBody UserAuth userAuth) {
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