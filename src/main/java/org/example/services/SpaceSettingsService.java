package org.example.services;

import org.example.domain.dto.SpaceSettingsDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface SpaceSettingsService {
    SpaceSettingsDto saveSpaceSettings(SpaceSettingsDto spaceSettingsDto, UserDetails user);
    SpaceSettingsDto updateSpaceSettings(SpaceSettingsDto spaceSettingsDto, UserDetails user);
    SpaceSettingsDto findSpaceSettingsById(Long id);
    void removeSpaceSettingsById(Long id);
}
