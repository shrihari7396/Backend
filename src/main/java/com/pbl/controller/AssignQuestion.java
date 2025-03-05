package com.pbl.controller;

import com.pbl.service.QuestionAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/assign")
public class AssignQuestion {
    @Autowired
    private QuestionAssignmentService service;

    @GetMapping("/")
    public ResponseEntity<String> assignQuestion() {
        service.assignQuestionsRandomly();
        return ResponseEntity.status(HttpStatus.OK).body("All Question are Assigned Successfully!!!");
    }
}
