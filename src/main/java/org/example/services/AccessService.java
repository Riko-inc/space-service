package org.example.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.entities.SpaceMemberEntity;
import org.example.domain.entities.UserEntity;
import org.example.domain.entities.WorkspaceEntity;
import org.example.exceptions.EntityNotFoundException;
import org.example.repositories.SpaceMemberRepository;
import org.example.repositories.WorkspaceRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service("AccessService")
@RequiredArgsConstructor
@Slf4j
public class AccessService {
    private final WorkspaceRepository workspaceRepository;
    private final SpaceMemberRepository spaceMemberRepository;

    public boolean isWorkspaceMember(UserDetails userDetails, Long workspaceId) {
        List<SpaceMemberEntity.Role> roles = getAllRoles(userDetails, workspaceId);
        return roles.stream().anyMatch(role -> role == SpaceMemberEntity.Role.MEMBER || role == SpaceMemberEntity.Role.OWNER);
    }

    public boolean isWorkspaceReader(UserDetails userDetails, Long workspaceId) {
        List<SpaceMemberEntity.Role> roles = getAllRoles(userDetails, workspaceId);
        return roles.stream().anyMatch(role -> role == SpaceMemberEntity.Role.READER);
    }

    public boolean isWorkspaceReaderPrefix(UserDetails userDetails, String taskPrefix) {
        WorkspaceEntity workspace = workspaceRepository
                .findByTaskPrefix(taskPrefix)
                .orElseThrow(() -> new EntityNotFoundException("Workspace with id "+ taskPrefix +" not found"));

        List<SpaceMemberEntity.Role> roles = getAllRoles(userDetails, workspace.getWorkspaceId());
        return roles.stream().anyMatch(role -> role == SpaceMemberEntity.Role.READER);
    }

    public boolean isWorkspaceOwner(UserDetails userDetails, Long workspaceId) {
        List<SpaceMemberEntity.Role> roles = getAllRoles(userDetails, workspaceId);
        return roles.stream().anyMatch(role -> role == SpaceMemberEntity.Role.OWNER);
    }

    private List<SpaceMemberEntity.Role> getAllRoles(UserDetails userDetails, Long workspaceId) {
        UserEntity userEntity = (UserEntity) userDetails;

        WorkspaceEntity workspace = workspaceRepository
                .findById(workspaceId)
                .orElseThrow(() -> new EntityNotFoundException("Workspace with id "+ workspaceId +" not found"));

        log.info("Looking for space members in workspace: {} with user id: {}", workspaceId, userEntity.getUserId());

        SpaceMemberEntity memberEntity = spaceMemberRepository.findSpaceMemberEntityByUserIdAndWorkspace(userEntity.getUserId(), workspace)
                .orElseThrow(() -> new EntityNotFoundException("Member or workspace was not found"));


        if (Objects.equals(SpaceMemberEntity.Role.OWNER, memberEntity.getRole())) {
            return List.of(SpaceMemberEntity.Role.OWNER, SpaceMemberEntity.Role.MEMBER, SpaceMemberEntity.Role.READER);
        }

        if (Objects.equals(SpaceMemberEntity.Role.MEMBER, memberEntity.getRole())) {
            return List.of(SpaceMemberEntity.Role.MEMBER, SpaceMemberEntity.Role.READER);
        }

        return List.of(SpaceMemberEntity.Role.READER);
    }
}
