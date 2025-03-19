package com.pbl.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private  String username;
    private String password;
    private String name;
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
