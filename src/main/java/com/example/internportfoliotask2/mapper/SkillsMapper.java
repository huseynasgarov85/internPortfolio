package com.example.internportfoliotask2.mapper;

import com.example.internportfoliotask2.model.entity.postgre.Skills;
import com.example.internportfoliotask2.model.payload.SkillsPayload;
import com.example.internportfoliotask2.model.response.SkillsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SkillsMapper {
    private final UserMapper userMapper;

    public SkillsResponse mapToResponse(Skills skills) {
        return SkillsResponse
                .builder()
                .id(skills.getId())
                .level(skills.getLevel())
                .skillName(skills.getSkillName())
                .userResponse(userMapper.mapToUserResponse(skills.getUser()))
                .build();
    }
    public Skills mapToEntity(SkillsPayload skillsPayload){
        return Skills
                .builder()
                .skillName(skillsPayload.getSkillName())
                .level(skillsPayload.getLevel())
                .build();
    }

}
