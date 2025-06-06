package org.example.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.example.domain.dto.SpaceSettingsDto;
import org.example.services.SpaceSettingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/spaces/{workspaceId}/settings")
@SecurityRequirement(name = "JWT")
public class SpaceSettingsController {
    private final SpaceSettingsService spaceSettingsService;

    @GetMapping("/{settingsId}")
    public ResponseEntity<SpaceSettingsDto> getSpaceSettings(@AuthenticationPrincipal UserDetails user, @PathVariable Long settingsId, @PathVariable Long workspaceId) {
        return new ResponseEntity<>(spaceSettingsService.findSpaceSettingsById(settingsId, user), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SpaceSettingsDto> saveSettings(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceSettingsDto spaceSettingsDto, @PathVariable Long workspaceId) {
        return new ResponseEntity<>(spaceSettingsService.saveSpaceSettings(spaceSettingsDto, user), HttpStatus.CREATED);
    }

    @PutMapping("/{settingsId}")
    public ResponseEntity<SpaceSettingsDto> updateSettings(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceSettingsDto spaceSettingsDto, @PathVariable Long settingsId, @PathVariable Long workspaceId) {
        return new ResponseEntity<>(spaceSettingsService.updateSpaceSettings(spaceSettingsDto, user, settingsId), HttpStatus.OK);
    }

    @DeleteMapping("/{settingsId}")
    public ResponseEntity<HttpStatus> deleteSettings(@AuthenticationPrincipal UserDetails user, @PathVariable Long settingsId, @PathVariable Long workspaceId) {
        spaceSettingsService.removeSpaceSettingsById(settingsId, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
