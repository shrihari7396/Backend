package com.pbl.controller;

import com.pbl.helper.BooleanClass;
import com.pbl.model.Question;
import com.pbl.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/getQuestion/{studentId}")
    public ResponseEntity<Question> student(@PathVariable String studentId) {

        Question studentQestion = null;
        return ResponseEntity.status(200).body(null);
    }
}
