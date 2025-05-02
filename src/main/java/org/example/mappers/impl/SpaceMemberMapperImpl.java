package org.example.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.dto.SpaceMemberDto;
import org.example.domain.entities.SpaceMemberEntity;
import org.example.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpaceMemberMapperImpl implements Mapper<SpaceMemberEntity, SpaceMemberDto> {
    private final ModelMapper modelMapper;

    @Override
    public SpaceMemberDto mapToDto(SpaceMemberEntity spaceMemberEntity) {
        return modelMapper.map(spaceMemberEntity, SpaceMemberDto.class);
    }

    @Override
    public SpaceMemberEntity mapFromDto(SpaceMemberDto spaceMemberDto) {
        return modelMapper.map(spaceMemberDto, SpaceMemberEntity.class);
    }
}
