package org.example.services;

import org.example.domain.dto.SpaceSettingsDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface SpaceSettingsService {
    SpaceSettingsDto saveSpaceSettings(SpaceSettingsDto spaceSettingsDto, UserDetails user);
    SpaceSettingsDto updateSpaceSettings(SpaceSettingsDto spaceSettingsDto, UserDetails user, Long settingsId);
    SpaceSettingsDto findSpaceSettingsById(Long id, UserDetails user);
    void removeSpaceSettingsById(Long id, UserDetails user);
}
