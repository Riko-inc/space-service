package org.example.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.dto.WorkspaceDto;
import org.example.domain.entities.WorkspaceEntity;
import org.example.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkspaceMapperImpl implements Mapper<WorkspaceEntity, WorkspaceDto> {
    private final ModelMapper modelMapper;

    public WorkspaceDto mapToDto(WorkspaceEntity workspaceEntity) {
        return modelMapper.map(workspaceEntity, WorkspaceDto.class);
    }
    public WorkspaceEntity mapFromDto(WorkspaceDto workspaceDto) {
        return modelMapper.map(workspaceDto, WorkspaceEntity.class);
    }
}
