package com.pbl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private int problemId;

    @Lob
    @Column(nullable = false)
    private String question;

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST, orphanRemoval = false)
    private List<Student> students;
}
