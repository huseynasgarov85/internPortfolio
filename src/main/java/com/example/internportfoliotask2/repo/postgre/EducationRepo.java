package com.example.internportfoliotask2.repo.postgre;

import com.example.internportfoliotask2.model.entity.postgre.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationRepo extends JpaRepository<Education, Long> {
    List<Education> findAllByUser_Id(Long id);
    Optional<Education> findByIdAndUser_Id(Long id,Long userId);
}
