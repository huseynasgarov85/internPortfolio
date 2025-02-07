package com.example.internportfoliotask2.mapper;

import com.example.internportfoliotask2.model.entity.postgre.Project;
import com.example.internportfoliotask2.model.payload.ProjectRequest;
import com.example.internportfoliotask2.model.response.ProjectResponse;
import com.example.internportfoliotask2.model.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public Project toEntity(ProjectRequest request) {
        Project project = new Project();
        project.setProjectName(request.getName());
        project.setDescription(request.getDescription());
        return project;
    }

    public ProjectResponse toResponse(Project project) {
        UserResponse userResponse = new UserResponse(
                project.getUser().getUserName(),
                project.getUser().getEmail(),
                project.getUser().getId()
        );

        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setName(project.getProjectName());
        response.setDescription(project.getDescription());
        response.setUserResponse(userResponse);
        return response;
    }

    public void updateProjectFromDto(ProjectRequest request, Project existingProject) {
        if (request.getName() != null && !request.getName().isEmpty()) {
            existingProject.setProjectName(request.getName());
        }
        if (request.getDescription() != null && !request.getDescription().isEmpty()) {
            existingProject.setDescription(request.getDescription());
        }
    }
}