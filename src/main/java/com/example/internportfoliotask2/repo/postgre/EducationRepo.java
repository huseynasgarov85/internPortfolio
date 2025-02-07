package com.example.internportfoliotask2.service.education;

import com.example.internportfoliotask2.model.entity.postgre.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepo extends JpaRepository<Education, Long> {
}
