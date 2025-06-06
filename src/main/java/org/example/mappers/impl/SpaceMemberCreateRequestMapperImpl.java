package org.example.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.dto.requests.SpaceMemberAddRequest;
import org.example.domain.entities.SpaceMemberEntity;
import org.example.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpaceMemberCreateRequestMapperImpl implements Mapper<SpaceMemberEntity, SpaceMemberAddRequest> {
    private final ModelMapper modelMapper;


    @Override
    public SpaceMemberAddRequest mapToDto(SpaceMemberEntity entity) {
        return modelMapper.map(entity, SpaceMemberAddRequest.class);
    }

    @Override
    public SpaceMemberEntity mapFromDto(SpaceMemberAddRequest request) {
        return modelMapper.map(request, SpaceMemberEntity.class);
    }
}
