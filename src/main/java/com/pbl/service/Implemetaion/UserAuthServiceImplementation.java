package com.pbl.service.Implemetaion;


import com.pbl.model.Admin;
import com.pbl.model.Student;
import com.pbl.model.UserAuth;
import com.pbl.repository.AdminRepository;
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
    AuthenticationManager authenticationManager;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JWTService jwtService;

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
        adminRepository.save(new Admin(userAuth.getUsername(), userAuth.getPassword()));
        userAuth.setPassword(encoder.encode(userAuth.getPassword()));
        return repository.save(userAuth);
    }

    @Override
    public String verify(UserAuth userAuth) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuth.getUsername(), userAuth.getPassword()));
        if(auth!=null && auth.isAuthenticated()) {
            return jwtService.generateToken(userAuth.getUsername(), userAuth.getRole().toString());
        } else {
            return "Fail";
        }
    }
}
