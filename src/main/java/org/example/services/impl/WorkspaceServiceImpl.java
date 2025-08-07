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
import org.example.domain.enums.EventType;
import org.example.exceptions.InvalidRequestParameterException;
import org.example.mappers.Mapper;
import org.example.repositories.SpaceMemberRepository;
import org.example.repositories.WorkspaceRepository;
import org.example.services.WorkspaceService;
import org.example.utils.PublishDtoEvent;
import org.example.utils.PublishStringEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final SpaceMemberRepository spaceMemberRepository;
    private final Mapper<WorkspaceEntity, WorkspaceDto> mapper;
    private final Mapper<WorkspaceEntity, WorkspaceCreateRequest> workspaceCreateRequestMapper;

    @Override
    public List<WorkspaceDto> findAllWorkspaces(UserDetails user) {
        UserEntity userEntity = (UserEntity) user;
        return workspaceRepository.findByMembersUserId(userEntity.getUserId())
                .stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @Transactional
    @Override
    @PublishDtoEvent(eventType = EventType.SPACE_CREATED, topic = "space-events", payloadClass = WorkspaceDto.class)
    public WorkspaceDto createWorkspace(WorkspaceCreateRequest workspaceCreateRequest, UserDetails user) {
        if (workspaceRepository.existsByTaskPrefix(workspaceCreateRequest.getTaskPrefix())) {
            throw new InvalidRequestParameterException("Space with task prefix {} already exists", workspaceCreateRequest.getTaskPrefix());
        }

        UserEntity userEntity = (UserEntity) user;
        WorkspaceEntity workspace = workspaceCreateRequestMapper.mapFromDto(workspaceCreateRequest);
        workspace.setSettings(SpaceSettingsEntity.builder()
                .workspace(workspace)
                .build());

        SpaceMemberEntity owner = SpaceMemberEntity.builder()
                .role(SpaceMemberEntity.Role.OWNER)
                .userId(userEntity.getUserId())
                .workspace(workspace)
                .build();

        workspace.setMembers(new ArrayList<>(List.of(owner)));

        return mapper.mapToDto(workspaceRepository.save(workspace));
    }

    @Override
    public WorkspaceDto updateWorkspace(WorkspaceUpdateRequest workspaceUpdateRequest, UserDetails user, Long workspaceId) {
        UserEntity userEntity = (UserEntity) user;

        WorkspaceEntity workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new EntityNotFoundException("Workspace " + workspaceId + " not found"));

        workspace.setWorkspaceDescription(workspaceUpdateRequest.getWorkspaceDescription());
        workspace.setWorkspaceName(workspaceUpdateRequest.getWorkspaceName());

        return mapper.mapToDto(workspaceRepository.saveAndFlush(workspace));
    }

    @Override
    public void deleteWorkspaceById(Long id, UserDetails user) {
        workspaceRepository.deleteById(id);
    }

    @Override
    public WorkspaceDto findWorkspaceById(Long id, UserDetails user) {
        return mapper.mapToDto(workspaceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Workspace " + id + " not found")));
    }

    @Override
    public WorkspaceDto findWorkspaceByPrefix(String prefix, UserDetails user) {
        return mapper.mapToDto(workspaceRepository.findByTaskPrefix(prefix)
                .orElseThrow(() -> new EntityNotFoundException("Workspace with prefix " + prefix + " not found")));
    }
}
