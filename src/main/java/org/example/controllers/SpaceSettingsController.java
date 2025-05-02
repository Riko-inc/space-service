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
@RequestMapping("/api/v1/space_settings")
public class SpaceSettingsController {
    private final SpaceSettingsService spaceSettingsService;

    @SecurityRequirement(name = "JWT")
    @GetMapping("/{id}")
    public ResponseEntity<SpaceSettingsDto> getSpaceSettings(@PathVariable Long id) {
        return new ResponseEntity<>(spaceSettingsService.findSpaceSettingsById(id), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/save_settings")
    public ResponseEntity<SpaceSettingsDto> saveSettings(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceSettingsDto spaceSettingsDto) {
        return new ResponseEntity<>(spaceSettingsService.saveSpaceSettings(spaceSettingsDto, user), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping("/update_settings")
    public ResponseEntity<SpaceSettingsDto> updateSettings(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceSettingsDto spaceSettingsDto) {
        return new ResponseEntity<>(spaceSettingsService.updateSpaceSettings(spaceSettingsDto, user), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/delete_settings/{id}")
    public ResponseEntity<SpaceSettingsDto> deleteSettings(@PathVariable Long id) {
        spaceSettingsService.removeSpaceSettingsById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
