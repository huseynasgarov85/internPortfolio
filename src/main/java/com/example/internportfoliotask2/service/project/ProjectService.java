package com.example.internportfoliotask2.service.project;

import com.example.internportfoliotask2.model.payload.ProjectRequest;
import com.example.internportfoliotask2.model.response.ProjectResponse;

import java.util.List;

public interface ProjectService {
    ProjectResponse createProject(ProjectRequest request, String token);
    ProjectResponse getProjectById(Long id, String token);
    List<ProjectResponse> getAllProjects(String token);
    ProjectResponse updateProject(Long id, ProjectRequest request, String token);
    void deleteProject(Long id, String token);
}