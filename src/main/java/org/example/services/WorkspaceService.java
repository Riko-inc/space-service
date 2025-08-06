package org.example.services;

import org.example.domain.dto.SpaceMemberDto;
import org.example.domain.dto.WorkspaceDto;
import org.example.domain.dto.requests.WorkspaceCreateRequest;
import org.example.domain.dto.requests.WorkspaceUpdateRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface WorkspaceService {
    List<WorkspaceDto> findAllWorkspaces(UserDetails user);
    WorkspaceDto createWorkspace(WorkspaceCreateRequest workspaceCreateRequest, UserDetails user);
    WorkspaceDto updateWorkspace(WorkspaceUpdateRequest workspaceUpdateRequest, UserDetails user, Long workspaceId);
    WorkspaceDto findWorkspaceById(Long id, UserDetails user);
    void deleteWorkspaceById(Long id, UserDetails user);
}
