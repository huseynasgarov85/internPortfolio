package com.example.internportfoliotask2.service.education;

import com.example.internportfoliotask2.globalException.exceptions.NotFoundException;
import com.example.internportfoliotask2.mapper.EducationMapper;
import com.example.internportfoliotask2.model.entity.postgre.Education;
import com.example.internportfoliotask2.model.entity.postgre.User;
import com.example.internportfoliotask2.model.payload.EducationPayload;
import com.example.internportfoliotask2.model.response.EducationResponse;
import com.example.internportfoliotask2.repo.postgre.EducationRepo;
import com.example.internportfoliotask2.util.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EducationServiceImpl implements EducationService {

    private final EducationRepo educationRepo;
    private final EducationMapper educationMapper;
    private final JwtTokenUtil jwtUtil;

    @Override
    public List<EducationResponse> getAll(String token) {
        log.info("ActionLog started getAll");
        User user = jwtUtil.getUserById(token);
        List<Education> education = educationRepo.findAllByUser_Id(user.getId());
        log.info("ActionLog end getAll");
        return education.stream().map(educationMapper::mapToResponse).toList();
    }

    @Override
    public EducationResponse getById(Long id, String token) {
        log.info("ActionLog started getById");
        User user = jwtUtil.getUserById(token);
        Education education = educationRepo.findByIdAndUser_Id(id, user.getId()).orElseThrow(() -> new NotFoundException("this id not founded"));
        log.info("ActionLog end getById");
        return educationMapper.mapToResponse(education);
    }

    @Override
    public void updateEducation(Long id, EducationPayload educationPayload, String token) {
        log.info("ActionLog started updateEducation " + id);
        User user = jwtUtil.getUserById(token);
        Education education = educationRepo.findByIdAndUser_Id(id, user.getId()).orElseThrow(() -> new NotFoundException("education not founded"));
        educationMapper.mapToEntity(educationPayload);
        educationRepo.save(education);
        log.info("ActionLog end updateEducation " + id);
    }

    @Override
    public EducationResponse save(EducationPayload educationPayload, String token) {
        log.info("ActionLog started save " + educationPayload.getStartDate());
        User user = jwtUtil.getUserById(token);
        Education education = educationMapper.mapToEntity(educationPayload);
        education.setUser(user);
        educationRepo.save(education);
        log.info("ActionLog end save " + educationPayload.getStartDate());
        return educationMapper.mapToResponse(education);
    }

    @Override
    public void delete(Long id, String token) {
        log.info("ActionLog started delete " + id);
        User user = jwtUtil.getUserById(token);
        Education education = educationRepo.findByIdAndUser_Id(id, user.getId()).orElseThrow(() -> new NotFoundException("not founded"));
        educationRepo.delete(education);
        log.info("ActionLog end delete " + id);
    }
}
