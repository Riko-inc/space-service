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
@RequestMapping("/api/v1/space_settings") //TODO: Заменить space_settings на /space/settings. _ не поддерживается в некоторых браузерах
public class SpaceSettingsController {
    private final SpaceSettingsService spaceSettingsService;

    // TODO: Добавить пользователя в аргументы, понадобится для проверки прав
    @SecurityRequirement(name = "JWT")
    @GetMapping("/{id}")
    public ResponseEntity<SpaceSettingsDto> getSpaceSettings(@PathVariable Long id) {
        return new ResponseEntity<>(spaceSettingsService.findSpaceSettingsById(id), HttpStatus.OK);
    }

    // TODO: Заменить статус код на CREATED
    // TODO: Убрать /save_settings. Только после смены следующего эндпоинта на PUT
    @SecurityRequirement(name = "JWT")
    @PostMapping("/save_settings")
    public ResponseEntity<SpaceSettingsDto> saveSettings(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceSettingsDto spaceSettingsDto) {
        return new ResponseEntity<>(spaceSettingsService.saveSpaceSettings(spaceSettingsDto, user), HttpStatus.OK);
    }

    // TODO: Заменить метод на PUT. Убрать /update_settings
    @SecurityRequirement(name = "JWT")
    @PostMapping("/update_settings")
    public ResponseEntity<SpaceSettingsDto> updateSettings(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceSettingsDto spaceSettingsDto) {
        return new ResponseEntity<>(spaceSettingsService.updateSpaceSettings(spaceSettingsDto, user), HttpStatus.OK);
    }

    // TODO: Добавить пользователя в аргументы, понадобится для проверки прав
    // TODO: Убрать /delete_settings. Заменить статус код на OK
    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/delete_settings/{id}")
    public ResponseEntity<SpaceSettingsDto> deleteSettings(@PathVariable Long id) {
        spaceSettingsService.removeSpaceSettingsById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
