package org.example.services;

import org.example.domain.dto.WorkspaceDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface WorkspaceService {
    //TODO: Добавить эндпоинт для получения всех пользователей в Workspace
    List<WorkspaceDto> findAllWorkspaces();
    WorkspaceDto saveWorkspace(WorkspaceDto workspaceDto, UserDetails user);
    WorkspaceDto updateWorkspace(WorkspaceDto workspaceDto, UserDetails user);
    WorkspaceDto findWorkspaceById(Long id);
    void deleteWorkspaceById(Long id);
}
