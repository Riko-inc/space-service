package org.example.mappers.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.dto.SpaceSettingsDto;
import org.example.domain.entities.SpaceSettingsEntity;
import org.example.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpaceSettingsMapperImpl implements Mapper<SpaceSettingsEntity, SpaceSettingsDto> {
    private final ModelMapper modelMapper;
    @Override
    public SpaceSettingsDto mapToDto(SpaceSettingsEntity spaceSettingsEntity) {
        return modelMapper.map(spaceSettingsEntity, SpaceSettingsDto.class);
    }

    @Override
    public SpaceSettingsEntity mapFromDto(SpaceSettingsDto spaceSettingsDto) {
        return modelMapper.map(spaceSettingsDto, SpaceSettingsEntity.class);
    }
}
