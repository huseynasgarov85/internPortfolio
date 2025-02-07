package com.example.internportfoliotask2.repo.postgre;

import com.example.internportfoliotask2.model.entity.postgre.Skills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillsRepo extends JpaRepository<Skills, Long> {
    List<Skills> findAllByUser_Id(Long id);
    Optional<Skills> findByIdAndUser_Id(Long id,Long userId);
    void deleteByIdAndUser_Id(Long id, Long userId);
}
