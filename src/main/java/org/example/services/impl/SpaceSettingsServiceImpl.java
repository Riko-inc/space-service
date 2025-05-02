package org.example.services.impl;

import lombok.AllArgsConstructor;
import org.example.domain.dto.SpaceSettingsDto;
import org.example.domain.entities.SpaceSettingsEntity;
import org.example.mappers.Mapper;
import org.example.repositories.SpaceSettingsRepository;
import org.example.services.SpaceSettingsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SpaceSettingsServiceImpl implements SpaceSettingsService {
    private final SpaceSettingsRepository spaceSettingsRepository;
    private final Mapper<SpaceSettingsEntity, SpaceSettingsDto> mapper;
    @Override
    public SpaceSettingsDto saveSpaceSettings(SpaceSettingsDto spaceSettingsDto, UserDetails user) {
        return mapper.mapToDto(spaceSettingsRepository.save( mapper.mapFromDto(spaceSettingsDto)));
    }

    @Override
    public SpaceSettingsDto updateSpaceSettings(SpaceSettingsDto spaceSettingsDto, UserDetails user) {
        return mapper.mapToDto(spaceSettingsRepository.save( mapper.mapFromDto(spaceSettingsDto)));
    }

    @Override
    public SpaceSettingsDto findSpaceSettingsById(Long id) {
        return mapper.mapToDto(spaceSettingsRepository.findById(id).orElse(null));
    }

    @Override
    public void removeSpaceSettingsById(Long id) {
        spaceSettingsRepository.deleteById(id);
    }
}
