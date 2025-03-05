package com.pbl.service.Implemetaion;


import com.pbl.model.Student;
import com.pbl.model.UserAuth;
import com.pbl.repository.UserAuthRepository;
import com.pbl.service.JWTService;
import com.pbl.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServiceImplementation implements UserAuthService {

    @Autowired
    UserAuthRepository repository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    JWTService jwtService;

    @Autowired
    private AuthenticationManager manager;

    @Override
    public String verifyUserAuth(UserAuth userAuth) {
        Authentication authentication = this.manager.authenticate(new UsernamePasswordAuthenticationToken(userAuth.getUsername(), userAuth.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(userAuth.getUsername());
        }
        return "Success";
    }

    public Student addStudent(Student student) {
        UserAuth auth = this.repository.findByUsername(student.getUsername());
        if(auth!=null) {
            throw new DuplicateKeyException("Student With "+student.getUsername()+" is alreay Exits!!!");
        }
        UserAuth userAuth = new UserAuth();
        userAuth.setUsername(student.getUsername());
        userAuth.setPassword(encoder.encode(student.getPassword()));
        userAuth.setRole(student.getRole());
        this.repository.save(userAuth);
        return student;
    }

    @Override
    public void removeStudent(String username) {
        this.repository.deleteByUsername(username);
    }

    @Override
    public UserAuth register(UserAuth userAuth) {
        return this.repository.save(userAuth);
    }
}
