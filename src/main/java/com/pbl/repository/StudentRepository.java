package com.pbl.repository;

import com.pbl.model.Student;
import com.pbl.service.StudentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    void deleteByUsername(String username);
    Student findByUsername(String username);
}
