package com.pbl.service.Implemetaion;


import com.pbl.exception.QuestionNotFoundException;
import com.pbl.model.Question;
import com.pbl.model.Student;
import com.pbl.model.UserAuth;
import com.pbl.model.UserDetailsImplementation;
import com.pbl.repository.QuestionRepository;
import com.pbl.repository.StudentRepository;
import com.pbl.repository.UserAuthRepository;
import com.pbl.service.QuestionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImplementation implements QuestionService {

    @Autowired
    private QuestionRepository repository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Question addQuestion(Question question) {
        if (this.repository.existsByProblemId(question.getProblemId())) {
            throw new DuplicateKeyException("A question with problemId " + question.getProblemId() + " already exists.");
        }
        return this.repository.save(question);
    }

    @Override
    public Boolean isExistsByProblemId(int problemId) {
        return this.repository.existsByProblemId(problemId);
    }

    @Override
    @Transactional
    public void removeQuestionById(int problemId) {
        Question question = repository.findByProblemId(problemId)
                .orElseThrow(() -> new QuestionNotFoundException("A question with problemId " + problemId + " already exists."));
        // Break relationships before deletion
        for (Student student : question.getStudents()) {
            student.setQuestion(null); // Set question to null instead of deleting
            studentRepository.save(student);
        }
//        question.getStudents().clear();

        // Now delete the question
        repository.delete(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return repository.findAll();
    }

    public static class JWTServiceImplementation {
    }
}