package com.pbl.service;

import com.pbl.model.Student;
import com.pbl.model.UserAuth;

public interface UserAuthService {
    Student addStudent(Student student);
    void removeStudent(String student);
    UserAuth register(UserAuth userAuth);
    String verify(UserAuth userAuth);
}
