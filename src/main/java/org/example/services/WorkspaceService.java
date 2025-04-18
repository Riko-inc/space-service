package org.example.services;

import org.example.domain.dto.WorkspaceDto;
import java.util.List;

public interface WorkspaceService {
    List<WorkspaceDto> findAllWorkspaces();
    WorkspaceDto saveWorkspace(WorkspaceDto workspaceDto);
    WorkspaceDto updateWorkspace(WorkspaceDto workspaceDto);
    void deleteWorkspaceById(long id);
    WorkspaceDto findWorkspaceById(long id);
}
