package com.pbl.service.Implemetaion;

import com.pbl.model.Question;
import com.pbl.model.Student;
import com.pbl.repository.QuestionRepository;
import com.pbl.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class QuestionAssignmentService implements com.pbl.service.QuestionAssignmentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void assignQuestionsRandomly() {
        List<Student> students = studentRepository.findAll();
        List<Question> questions = questionRepository.findAll();

        if (students.isEmpty() || questions.isEmpty()) {
            System.out.println("No students or questions available for assignment.");
            return;
        }

        Random random = new Random();

        for (Student student : students) {
            Question randomQuestion = questions.get(random.nextInt(questions.size())); // Pick a random question
            student.setQuestion(randomQuestion); // Assign it to student
        }

        studentRepository.saveAll(students); // Save updated students
        System.out.println("Questions assigned randomly to all students.");
    }
}
