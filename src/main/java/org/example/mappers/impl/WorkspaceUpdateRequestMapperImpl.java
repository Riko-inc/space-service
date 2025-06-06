package org.example.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.dto.requests.WorkspaceUpdateRequest;
import org.example.domain.entities.WorkspaceEntity;
import org.example.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkspaceUpdateRequestMapperImpl implements Mapper<WorkspaceEntity, WorkspaceUpdateRequest> {
    private final ModelMapper modelMapper;
    @Override
    public WorkspaceUpdateRequest mapToDto(WorkspaceEntity workspaceEntity) {
        return modelMapper.map(workspaceEntity, WorkspaceUpdateRequest.class);
    }

    @Override
    public WorkspaceEntity mapFromDto(WorkspaceUpdateRequest workspaceUpdateRequest) {
        return modelMapper.map(workspaceUpdateRequest, WorkspaceEntity.class);
    }
}
