package com.pbl.controller;

import com.pbl.model.Question;
import com.pbl.model.Student;
import com.pbl.repository.StudentRepository;
import com.pbl.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService service;

    // For Students

    @GetMapping("/allStudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getAllStudents());
    }

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        try {
            Student addedStudent = this.service.addStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully!");
        } catch (DuplicateKeyException e) {  // Catch duplicate username exception
            System.out.println(student);
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Student with username: " + student.getUsername() + " already exists!");
        } catch (Exception e) {  // Catch other unexpected exceptions
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while adding the student!!!");
        }
    }

    @Autowired
    private StudentRepository repository;

    @DeleteMapping("/delete/student/{username}")
    public ResponseEntity<String> removeStudent(@PathVariable String username) {
//        try {
//            this.service.removeStudentByUsername(username);
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body("Student with username: " + username + " removed successfully.");
//        } catch (NoSuchElementException e) {  // Handle not found cases
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("No student found with username: " + username);
//        } catch (Exception e) {
//            System.out.println(e.getClass());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(/*"An error occurred while deleting the student."*/ e.getClass().toString());
//        }

        Student student = this.repository.findByUsername(username);
        if(student==null) {
            throw new NoSuchElementException("No student found with username: "+username);
        }
        student.setQuestion(null);
        this.repository.delete(student);
        return ResponseEntity.status(HttpStatus.OK).body("Student removed successfully!");
    }

    // For Questions

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getAllQuestions());
    }

    @PostMapping("/addQuestion")  // Changed from @GetMapping to @PostMapping
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        try {
            Question addedQuestion = this.service.addQuestion(question);
            return ResponseEntity.status(HttpStatus.OK).body("Question added successfully!");
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Question with problemId: " + question.getProblemId() + " already exists!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the question.");
        }
    }

    @DeleteMapping("/delete/question/{problemId}")
    public ResponseEntity<String> removeQuestion(@PathVariable int problemId) {
        try {
            this.service.removeQuestionByQuestionId(problemId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("question with problemId: " + problemId + " removed successfully.");
        } catch (NoSuchElementException e) {  // Handle not found cases
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No question found with problemId: " + problemId);
        } catch (Exception e) {
            System.out.println(e.getClass());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getClass().toString());
        }
    }

    @GetMapping("/")
    public String sayHello() {
        return "Hello, This is an Admin Speaking!!!";
    }
}