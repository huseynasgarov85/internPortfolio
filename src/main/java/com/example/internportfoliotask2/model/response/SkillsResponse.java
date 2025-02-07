package com.example.internportfoliotask2.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillsResponse {
    private Long id;
    private String skillName;
    private String level;
    private UserResponse userResponse;
}
