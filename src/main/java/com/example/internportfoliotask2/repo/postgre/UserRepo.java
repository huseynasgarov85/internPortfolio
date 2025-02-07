package com.example.internportfoliotask2.repo.postgre;

import com.example.internportfoliotask2.model.entity.postgre.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findUserById(Long id);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUserName(String username);


}
