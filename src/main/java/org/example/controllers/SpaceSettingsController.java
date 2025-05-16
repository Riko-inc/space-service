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
@RequestMapping("/api/v1/space/settings")
public class SpaceSettingsController {
    private final SpaceSettingsService spaceSettingsService;

    @SecurityRequirement(name = "JWT")
    @GetMapping("/{id}")
    public ResponseEntity<SpaceSettingsDto> getSpaceSettings(@AuthenticationPrincipal UserDetails user, @PathVariable Long id) {
        return new ResponseEntity<>(spaceSettingsService.findSpaceSettingsById(id, user), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping
    public ResponseEntity<SpaceSettingsDto> saveSettings(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceSettingsDto spaceSettingsDto) {
        return new ResponseEntity<>(spaceSettingsService.saveSpaceSettings(spaceSettingsDto, user), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "JWT")
    @PutMapping("/{id}")
    public ResponseEntity<SpaceSettingsDto> updateSettings(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceSettingsDto spaceSettingsDto, @PathVariable Long id) {
        return new ResponseEntity<>(spaceSettingsService.updateSpaceSettings(spaceSettingsDto, user, id), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/{id}")
    public ResponseEntity<SpaceSettingsDto> deleteSettings(@AuthenticationPrincipal UserDetails user, @PathVariable Long id) {
        spaceSettingsService.removeSpaceSettingsById(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
