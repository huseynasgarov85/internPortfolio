package com.example.internportfoliotask2.controller;

import com.example.internportfoliotask2.model.payload.ProjectRequest;
import com.example.internportfoliotask2.model.response.ProjectResponse;
import com.example.internportfoliotask2.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ProjectResponse createProject(@RequestBody ProjectRequest request,
                                         @RequestHeader("Authorization") String token) {
        return projectService.createProject(request, token);
    }

    @GetMapping("/{id}")
    public ProjectResponse getProjectById(@PathVariable Long id,
                                          @RequestHeader("Authorization") String token) {
        return projectService.getProjectById(id, token);
    }

    @GetMapping
    public List<ProjectResponse> getAllProjects(@RequestHeader("Authorization") String token) {
        return projectService.getAllProjects(token);
    }

    @PutMapping("/{id}")
    public ProjectResponse updateProject(@PathVariable Long id,
                                         @RequestBody ProjectRequest request,
                                         @RequestHeader("Authorization") String token) {
        return projectService.updateProject(id, request, token);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id,
                              @RequestHeader("Authorization") String token) {
        projectService.deleteProject(id, token);
    }
}