package org.example.services.impl;

import lombok.AllArgsConstructor;
import org.example.domain.dto.WorkspaceDto;
import org.example.domain.entities.SpaceMemberEntity;
import org.example.domain.entities.SpaceSettingsEntity;
import org.example.domain.entities.UserEntity;
import org.example.domain.entities.WorkspaceEntity;
import org.example.mappers.Mapper;
import org.example.repositories.WorkspaceRepository;
import org.example.services.WorkspaceService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final Mapper<WorkspaceEntity, WorkspaceDto> mapper;

    @Override
    public List<WorkspaceDto> findAllWorkspaces() {
        return workspaceRepository.findAll().stream().map(mapper::mapToDto).toList();
    }

    @Override
    public WorkspaceDto saveWorkspace(WorkspaceDto workspaceDto, UserDetails user) {
        UserEntity userEntity = (UserEntity) user;
        WorkspaceEntity workspace = mapper.mapFromDto(workspaceDto)
                .setOwnerId(userEntity.getUserId())
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now());

        SpaceSettingsEntity settings = SpaceSettingsEntity.builder()
                .workspace(workspace)
                .build();
        workspace.setSettings(settings);

        SpaceMemberEntity ownerMember = SpaceMemberEntity.builder()
                .role(SpaceMemberEntity.Role.OWNER)
                .invitedDateTime(LocalDateTime.now())
                .workspace(workspace)
                .build();

        workspace.getMembers().add(ownerMember);
        return mapper.mapToDto(workspaceRepository.save(workspace));

    }

    @Override
    public WorkspaceDto updateWorkspace(WorkspaceDto workspaceDto, UserDetails user) {
        UserEntity userEntity = (UserEntity) user;
        return mapper.mapToDto(workspaceRepository.save(
                mapper.mapFromDto(workspaceDto)
                        .setUpdatedAt(LocalDateTime.now())
        ));
    }

    @Override
    public void deleteWorkspaceById(Long id) {
        workspaceRepository.deleteById(id);
    }

    @Override
    public WorkspaceDto findWorkspaceById(Long id) {
        return mapper.mapToDto(workspaceRepository.findById(id).orElse(null));
    }
}
