package com.pbl.repository;

import com.pbl.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    boolean existsByProblemId(int problemId);
    void deleteByProblemId(int problemId);
    Optional<Question> findByProblemId(int problemId);
}