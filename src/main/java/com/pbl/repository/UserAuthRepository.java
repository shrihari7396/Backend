package com.pbl.repository;

import com.pbl.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
    UserAuth findByUsername(String username);
    void deleteByUsername(String username);
}
