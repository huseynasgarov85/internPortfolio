package com.example.internportfoliotask2.service.skills;

import com.example.internportfoliotask2.globalException.exceptions.NotFoundException;
import com.example.internportfoliotask2.mapper.SkillsMapper;
import com.example.internportfoliotask2.model.entity.postgre.Skills;
import com.example.internportfoliotask2.model.entity.postgre.User;
import com.example.internportfoliotask2.model.payload.SkillsPayload;
import com.example.internportfoliotask2.model.response.SkillsResponse;
import com.example.internportfoliotask2.repo.postgre.SkillsRepo;
import com.example.internportfoliotask2.util.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SkillsServiceImpl implements SkillsService {
    private final SkillsRepo skillsRepo;
    private final JwtTokenUtil jwtUtil;
    private final SkillsMapper skillsMapper;

    @Override
    public List<SkillsResponse> getAll(String token) {
        log.info("ActionLog started getAll");
        User user = jwtUtil.getUserById(token);
        List<Skills> skills = skillsRepo.findAllByUser_Id(user.getId());
        log.info("ActionLog end getAll");
        return skills.stream().map(skillsMapper::mapToResponse).toList();
    }

    @Override
    public SkillsResponse getById(Long id, String token) {
        log.info("ActionLog started " + id);
        User user = jwtUtil.getUserById(token);
        Skills skills = skillsRepo.findByIdAndUser_Id(id, user.getId()).orElseThrow(() -> new NotFoundException("skills id not founded"));
        log.info("ActionLog end " + id);
        return skillsMapper.mapToResponse(skills);
    }

    @Override
    public SkillsResponse create(SkillsPayload skillsPayload, String token) {
        log.info("ActionLog started " + skillsPayload.getSkillName());
        User user = jwtUtil.getUserById(token);
        Skills skills = skillsMapper.mapToEntity(skillsPayload);
        skills.setUser(user);
        skillsRepo.save(skills);
        log.info("ActionLog end " + skillsPayload.getSkillName());
        return skillsMapper.mapToResponse(skills);
    }

    @Override
    public SkillsResponse update(Long id, SkillsPayload skillsPayload, String token) {
        log.info("ActionLog started " + id);
        User user = jwtUtil.getUserById(token);
        Skills skills = skillsRepo.findByIdAndUser_Id(id, user.getId()).orElseThrow(() -> new NotFoundException("skill id not founded"));
        skillsMapper.mapToEntity(skillsPayload);
        skillsRepo.save(skills);
        log.info("ActionLog end " + id);
        return skillsMapper.mapToResponse(skills);
    }

    @Override
    public void deleteSkill(Long id, String token) {
        log.info("ActionLog started " + id);
        User user = jwtUtil.getUserById(token);
        skillsRepo.deleteByIdAndUser_Id(id, user.getId());
        log.info("ActionLog end " + id);
    }
}
