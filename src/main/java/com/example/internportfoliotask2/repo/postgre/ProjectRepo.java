package com.example.internportfoliotask2.repo.postgre;

import com.example.internportfoliotask2.model.entity.postgre.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepo extends JpaRepository<Project, Long> {
    List<Project> findAllByUserId(Long userId);

    Optional<Project> findByIdAndUserId(Long id, Long userId);

}
