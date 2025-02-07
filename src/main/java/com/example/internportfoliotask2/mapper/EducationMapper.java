package com.example.internportfoliotask2.mapper;

import com.example.internportfoliotask2.model.entity.postgre.Education;
import com.example.internportfoliotask2.model.payload.EducationPayload;
import com.example.internportfoliotask2.model.response.EducationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EducationMapper {
    private final UserMapper userMapper;

    public EducationResponse mapToResponse(Education education) {
        return EducationResponse
                .builder()
                .id(education.getId())
                .gpa(education.getGpa())
                .academicStatus(education.getAcademicStatus())
                .degree(education.getDegree())
                .endDate(education.getEndDate())
                .institution(education.getInstitution())
                .startDate(education.getStartDate())
                .userResponse(userMapper.mapToUserResponse(education.getUser()))
                .build();
    }
    public Education mapToEntity(EducationPayload educationPayload) {
        return Education
                .builder()
                .academicStatus(educationPayload.getAcademicStatus())
                .degree(educationPayload.getDegree())
                .endDate(educationPayload.getEndDate())
                .gpa(educationPayload.getGpa())
                .institution(educationPayload.getInstitution())
                .startDate(educationPayload.getStartDate())
                .build();
    }
}
