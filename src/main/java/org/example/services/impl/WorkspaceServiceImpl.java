package org.example.services.impl;

import lombok.AllArgsConstructor;
import org.example.domain.dto.WorkspaceDto;
import org.example.domain.entities.WorkspaceEntity;
import org.example.mappers.Mapper;
import org.example.repositories.WorkspaceRepository;
import org.example.services.WorkspaceService;
import org.springframework.stereotype.Service;

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
    public WorkspaceDto saveWorkspace(WorkspaceDto workspaceDto) {
        return mapper.mapToDto(workspaceRepository.save(mapper.mapFromDto(workspaceDto)));
    }

    @Override
    public WorkspaceDto updateWorkspace(WorkspaceDto workspaceDto) {
        return mapper.mapToDto(workspaceRepository.save(mapper.mapFromDto(workspaceDto)));
    }

    @Override
    public void deleteWorkspaceById(long id) {
        workspaceRepository.deleteById(id);
    }

    @Override
    public WorkspaceDto findWorkspaceById(long id) {
        return mapper.mapToDto(workspaceRepository.findById(id).orElse(null));
    }
}
