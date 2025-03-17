package com.pbl.service.Implemetaion;


import com.pbl.model.Admin;
import com.pbl.model.Student;
import com.pbl.model.UserAuth;
import com.pbl.repository.AdminRepository;
import com.pbl.repository.UserAuthRepository;
import com.pbl.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthServiceImplementation implements UserAuthService {

    @Autowired
    UserAuthRepository repository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    private AdminRepository adminRepository;

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
        return repository.save(userAuth);
    }
}
