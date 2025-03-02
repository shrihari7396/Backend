package com.pbl.repository;

import com.pbl.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
    UserAuth findByUsername(String username);
    UserAuth deleteByUsername(String username);
}
