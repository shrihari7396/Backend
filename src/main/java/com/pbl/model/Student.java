package com.pbl.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ROLE role = ROLE.STUDENT;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = true)
    @JsonBackReference
    private Question question;
}
