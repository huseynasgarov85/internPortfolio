package com.example.internportfoliotask2.controller;

import com.example.internportfoliotask2.model.payload.SkillsPayload;
import com.example.internportfoliotask2.model.response.SkillsResponse;
import com.example.internportfoliotask2.service.skills.SkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@RequiredArgsConstructor
public class SkillsController {
    private final SkillsService skillsService;


    @GetMapping("/getSkillsAll")
    public List<SkillsResponse> getAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return skillsService.getAll(token);
    }

    @GetMapping
    public SkillsResponse getById(@RequestParam(value = "id") Long id, String token) {
        return skillsService.getById(id, token);
    }

    @PostMapping
    public SkillsResponse create(@RequestBody SkillsPayload skillsPayload,
                                 @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return skillsService.create(skillsPayload, token);
    }

    @PutMapping
    public SkillsResponse update(@RequestParam(value = "skills_id") Long id,
                                 @RequestBody SkillsPayload skillsPayload,
                                 @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return skillsService.update(id, skillsPayload, token);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Long id,
                            @RequestHeader("Authorization") String token) {
        skillsService.deleteSkill(id, token);
    }
}
