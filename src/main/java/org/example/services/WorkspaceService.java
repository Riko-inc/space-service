package org.example.services;

import org.example.domain.dto.SpaceMemberDto;
import org.example.domain.dto.WorkspaceDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.Member;
import java.util.List;

public interface WorkspaceService {
    List<WorkspaceDto> findAllWorkspaces();
    List<SpaceMemberDto> findAllSpaceMembers(WorkspaceDto workspace, UserDetails user );
    WorkspaceDto saveWorkspace(WorkspaceDto workspaceDto, UserDetails user);
    WorkspaceDto updateWorkspace(WorkspaceDto workspaceDto, UserDetails user);
    WorkspaceDto findWorkspaceById(Long id);
    void deleteWorkspaceById(Long id);
}
