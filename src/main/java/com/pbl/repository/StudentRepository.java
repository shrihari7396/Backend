package com.pbl.repository;

import com.pbl.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    void deleteByUsername(String username);
    Student findByUsername(String username);
    Boolean existsByUsername(String username);
}
