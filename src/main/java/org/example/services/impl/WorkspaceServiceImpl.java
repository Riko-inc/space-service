package org.example.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.domain.dto.SpaceMemberDto;
import org.example.domain.dto.WorkspaceDto;
import org.example.domain.dto.requests.WorkspaceCreateRequest;
import org.example.domain.dto.requests.WorkspaceUpdateRequest;
import org.example.domain.entities.SpaceMemberEntity;
import org.example.domain.entities.SpaceSettingsEntity;
import org.example.domain.entities.UserEntity;
import org.example.domain.entities.WorkspaceEntity;
import org.example.mappers.Mapper;
import org.example.repositories.SpaceMemberRepository;
import org.example.repositories.WorkspaceRepository;
import org.example.services.WorkspaceService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final SpaceMemberRepository spaceMemberRepository;
    private final Mapper<WorkspaceEntity, WorkspaceDto> mapper;
    private final Mapper<WorkspaceEntity, WorkspaceCreateRequest> workspaceCreateRequestMapper;
    private final Mapper<WorkspaceEntity, WorkspaceUpdateRequest> workspaceUpdateRequestMapper;

    @Override
    public List<WorkspaceDto> findAllWorkspaces(UserDetails user) {
        return workspaceRepository.findAll().stream().map(mapper::mapToDto).toList();
    }

    @Override
    public List<SpaceMemberDto> findAllSpaceMembers(WorkspaceCreateRequest workspace, UserDetails user) {
        return List.of();
    }

    @Transactional
    @Override
    public WorkspaceDto saveWorkspace(WorkspaceCreateRequest workspaceCreateRequest, UserDetails user) {
        UserEntity userEntity = (UserEntity) user;
        WorkspaceEntity workspace = workspaceCreateRequestMapper.mapFromDto(workspaceCreateRequest)
                .setOwnerId(userEntity.getUserId());
        workspace.setSettings(SpaceSettingsEntity.builder()
                .workspace(workspace)
                .build());


        SpaceMemberEntity ownerMember = SpaceMemberEntity.builder()
                .role(SpaceMemberEntity.Role.OWNER)
                .invitedDateTime(LocalDateTime.now())
                .workspace(workspace)
                .build();

        workspace.setMembers(List.of(ownerMember));

        return mapper.mapToDto(workspaceRepository.save(workspace));
    }

    @Override
    public WorkspaceDto updateWorkspace(WorkspaceUpdateRequest workspaceUpdateRequest, UserDetails user) {
        UserEntity userEntity = (UserEntity) user;
        workspaceUpdateRequest.getMembers().forEach(newMember -> {
            if (spaceMemberRepository.findById(newMember.getMemberId()).isEmpty()) {
                spaceMemberRepository.save(SpaceMemberEntity.builder()
                        .memberId(newMember.getMemberId())
                        .role(newMember.getRole())
                        .invitedByMember((spaceMemberRepository.findById(userEntity.getUserId())
                                .orElseThrow(() -> new EntityNotFoundException("Member " + newMember.getMemberId() + " not found"))))
                        .build());
            }
        });

        workspaceRepository.findById(workspaceUpdateRequest.getWorkspaceId())
                .orElseThrow(() -> new EntityNotFoundException("Workspace " + workspaceUpdateRequest.getWorkspaceId() + " not found"));
        return mapper.mapToDto(workspaceRepository.save(
                workspaceUpdateRequestMapper.mapFromDto(workspaceUpdateRequest)));
    }

    @Override
    public void deleteWorkspaceById(Long id, UserDetails user) {
        workspaceRepository.deleteById(id);
    }

    @Override
    public WorkspaceDto findWorkspaceById(Long id, UserDetails user) {
        return mapper.mapToDto(workspaceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Workspace " + id + " not found")));
    }
}
