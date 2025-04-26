package org.example.services;

import org.example.domain.dto.WorkspaceDto;
import org.example.domain.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.List;

public interface WorkspaceService {
    List<WorkspaceDto> findAllWorkspaces();
    WorkspaceDto saveWorkspace(WorkspaceDto workspaceDto, UserDetails user);
    WorkspaceDto updateWorkspace(WorkspaceDto workspaceDto);
    void deleteWorkspaceById(long id);
    WorkspaceDto findWorkspaceById(long id);
}
