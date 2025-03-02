package com.pbl.service;

import com.pbl.model.Question;

import java.util.List;

public interface QuestionService {

    Question addQuestion(Question question);
    Boolean isExistsByProblemId(int problemId);
    void removeQuestionById(int problemId);
    List<Question> getAllQuestions();
}
