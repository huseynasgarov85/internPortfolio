package com.example.internportfoliotask2.service.education;

import com.example.internportfoliotask2.model.payload.EducationPayload;
import com.example.internportfoliotask2.model.response.EducationResponse;

import java.util.List;

public interface EducationService {
    List<EducationResponse> getAll(String token);

    EducationResponse getById(Long id, String token);

    void updateEducation(Long id, EducationPayload educationPayload, String token);

    EducationResponse save(EducationPayload educationPayload, String token);

    void delete(Long id, String token);
}
