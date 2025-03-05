package com.pbl.service;

import com.pbl.model.Question;
import com.pbl.model.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);
    void removeStudentByUsername(String username);
    Question getQuestionByStudentUsername(String username);
    List<Student> getAllStudents();
    void check();
}
