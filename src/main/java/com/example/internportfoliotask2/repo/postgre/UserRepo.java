package com.example.internportfoliotask2.repo;

import com.example.internportfoliotask2.model.entity.postgre.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

}
