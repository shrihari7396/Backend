package com.pbl.service;

import com.pbl.model.Student;
import com.pbl.model.UserAuth;

public interface UserAuthService {
    String verifyUserAuth(UserAuth userAuth);
    Student addStudent(Student student);
    void removeStudent(String student);
    UserAuth register(UserAuth userAuth);
}
