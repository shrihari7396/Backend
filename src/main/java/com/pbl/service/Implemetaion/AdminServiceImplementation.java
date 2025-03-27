package com.pbl.service.Implemetaion;

import com.pbl.model.Admin;
import com.pbl.model.Question;
import com.pbl.model.Student;
import com.pbl.repository.AdminRepository;
import com.pbl.service.AdminService;
import com.pbl.service.QuestionService;
import com.pbl.service.StudentService;
import com.pbl.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdminServiceImplementation implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Admin Manipulation
    public Admin addadmin(Admin admin) {
        return this.adminRepository.save(admin);
    }
    public void removeAdminByUsername(String username) {
        this.adminRepository.deleteByUsername(username);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    // Question Manipulation
    @Autowired
    private QuestionService questionService;

    public Question addQuestion(Question question) {
        Question addQuestion;
        try {
            addQuestion = this.questionService.addQuestion(question);
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException("A question with problemId " + question.getProblemId() + " already exists.");
        }
        return addQuestion;
    }
    public  void removeQuestionByQuestionId(int problemId) {
        if(!this.questionService.isExistsByProblemId(problemId)) {
            throw new NoSuchElementException("No question found with problemId: " + problemId);
        }
        this.questionService.removeQuestionById(problemId);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // Student Manipulation
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserAuthService userAuthService;

    public Student addStudent(Student student) {
        Student addedStudent=null;
        try {
            addedStudent = this.studentService.addStudent(student);
            Student added = this.userAuthService.addStudent(student);
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException("Student with username: "+ student.getUsername()+" is Already Exists!!!");
        } catch (Exception e) {
            System.out.println(e.getClass()+"Shrihari");
            throw new RuntimeException("Unother Exeption has occured!!!");
        }
        return addedStudent;
    }

    public void removeStudentByUsername(String username) {
        try {
            this.studentService.removeStudentByUsername(username);
            this.userAuthService.removeStudent(username);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(e);
        } catch (Exception e) {
            System.out.println(e.getClass());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
}