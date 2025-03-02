package com.pbl.service;

import com.pbl.model.Admin;
import com.pbl.model.Question;
import com.pbl.model.Student;

import java.util.List;

public interface AdminService {
    // Admin
    Admin addadmin(Admin admin);
    void removeAdminByUsername(String username);

    // Question
    Question addQuestion(Question question);
    void removeQuestionByQuestionId(int problemId);
    List<Question> getAllQuestions();

    // Student
    Student addStudent(Student student);
    void removeStudentByUsername(String username);
    List<Student> getAllStudents();
}
