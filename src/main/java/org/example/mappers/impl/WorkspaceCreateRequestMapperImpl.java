package org.example.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.dto.WorkspaceDto;
import org.example.domain.dto.requests.WorkspaceCreateRequest;
import org.example.domain.entities.WorkspaceEntity;
import org.example.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkspaceCreateRequestMapperImpl implements Mapper<WorkspaceEntity, WorkspaceCreateRequest> {
    private final ModelMapper modelMapper;


    @Override
    public WorkspaceCreateRequest mapToDto(WorkspaceEntity workspaceEntity) {
        return modelMapper.map(workspaceEntity, WorkspaceCreateRequest.class);
    }

    @Override
    public WorkspaceEntity mapFromDto(WorkspaceCreateRequest workspaceCreateRequest) {
        return modelMapper.map(workspaceCreateRequest, WorkspaceEntity.class);
    }
}
