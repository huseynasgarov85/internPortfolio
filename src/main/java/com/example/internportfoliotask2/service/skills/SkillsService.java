package com.example.internportfoliotask2.service.skills;

import com.example.internportfoliotask2.model.payload.SkillsPayload;
import com.example.internportfoliotask2.model.response.SkillsResponse;

import java.util.List;

public interface SkillsService {
    List<SkillsResponse> getAll(String token);

    SkillsResponse getById(Long id, String token);

    SkillsResponse create(SkillsPayload skillsPayload, String token);

    SkillsResponse update(Long id, SkillsPayload skillsPayload, String token);

    void deleteSkill(Long id, String token);
}
