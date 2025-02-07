package com.example.internportfoliotask2.service.project;

import com.example.internportfoliotask2.mapper.ProjectMapper;
import com.example.internportfoliotask2.model.entity.postgre.Project;
import com.example.internportfoliotask2.model.entity.postgre.User;
import com.example.internportfoliotask2.model.payload.ProjectRequest;
import com.example.internportfoliotask2.model.response.ProjectResponse;
import com.example.internportfoliotask2.repo.postgre.ProjectRepo;
import com.example.internportfoliotask2.util.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepo projectRepository;
    private final ProjectMapper projectMapper;
    private final JwtTokenUtil jwtutil;

    @Override
    public ProjectResponse createProject(ProjectRequest request, String token) {
        User user = jwtutil.getUserById(token);
        Project project = projectMapper.toEntity(request);
        project.setUser(user);
        Project savedProject = projectRepository.save(project);
        return projectMapper.toResponse(savedProject);
    }

    @Override
    public ProjectResponse getProjectById(Long id, String token) {
        User user = jwtutil.getUserById(token);
        Project project = projectRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Project not found or access denied"));
        return projectMapper.toResponse(project);
    }

    @Override
    public List<ProjectResponse> getAllProjects(String token) {
        User user = jwtutil.getUserById(token);
        List<Project> projects = projectRepository.findAllByUserId(user.getId());
        return projects.stream()
                .map(projectMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, String token) {
        User user = jwtutil.getUserById(token);
        Project existingProject = projectRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Project not found or access denied"));
        projectMapper.updateProjectFromDto(request, existingProject);
        Project updatedProject = projectRepository.save(existingProject);
        return projectMapper.toResponse(updatedProject);
    }

    @Override
    public void deleteProject(Long id, String token) {
        User user = jwtutil.getUserById(token);
        Project project = projectRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new RuntimeException("Project not found or access denied"));
        projectRepository.delete(project);
    }
}