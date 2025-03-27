package com.pbl.repository;


import com.pbl.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    void deleteByUsername(String username);
    boolean existsByUsername(String username);
    Admin findByUsername(String username);
}