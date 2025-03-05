package com.pbl.service.Implemetaion;

import com.pbl.exception.QuestionNotAssignedException;
import com.pbl.helper.BooleanClass;
import com.pbl.model.Question;
import com.pbl.model.Student;
import com.pbl.repository.StudentRepository;
import com.pbl.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImplementation implements StudentService {

    @Autowired
    private StudentRepository repository;

    public Student addStudent(Student student) {
        Student student1=this.repository.findByUsername(student.getUsername());
        if (student1!=null) {
            throw new DuplicateKeyException("A question with problemId " + student.getUsername() + " already exists.");
        }
        return this.repository.save(student);
    }

    public void removeStudentByUsername(String username) {
        Student student = this.repository.findByUsername(username);
        if(student==null) {
            throw new NoSuchElementException("No student found with username: "+username);
        }
        student.setQuestion(null);
        this.repository.delete(student);
    }

    @Override
    public Question getQuestionByStudentUsername(String username) {
        System.out.println("Student: "+ username);
        Student student = repository.findByUsername(username);
        if(student.getQuestion()==null) {
            throw new QuestionNotAssignedException("Question Not Assigned Exception!!!");
        }
        return student.getQuestion();
    }

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public void check() {
        List<Student> students = repository.findAll();
        for (Student student : students) {
            if(student.getQuestion()==null) {
                BooleanClass.questionAssigned=false;
            }
        }
    }
}
