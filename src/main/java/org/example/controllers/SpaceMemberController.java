package org.example.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.example.domain.dto.SpaceMemberDto;
import org.example.services.SpaceMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/space_member") //TODO: Заменить space_member на /space/member. _ не поддерживается в некоторых браузерах
public class SpaceMemberController {
    private final SpaceMemberService spaceMemberService;

    // TODO: Добавить пользователя в аргументы, понадобится для проверки прав
    @SecurityRequirement(name = "JWT")
    @GetMapping("/{id}")
    public ResponseEntity<SpaceMemberDto> getSpaceMember(@PathVariable Long id) {
        return new ResponseEntity<>(spaceMemberService.findSpaceMemberById(id), HttpStatus.OK);
    }

    // TODO: убрать /save_space_member. Только после замены POST на PUT в следующем эндпоинте
    @SecurityRequirement(name = "JWT")
    @PostMapping("/save_space_member")
    public ResponseEntity<SpaceMemberDto> saveSpaceMember(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceMemberDto spaceMemberDto) {
        return new ResponseEntity<>(spaceMemberService.saveSpaceMember(spaceMemberDto, user), HttpStatus.CREATED);
    }

    // TODO: Заменить POST на PUT и убрать /update_space_member. Заменить статус код на OK
    @SecurityRequirement(name = "JWT")
    @PostMapping("/update_space_member")
    public ResponseEntity<SpaceMemberDto> updateSpaceMember(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceMemberDto spaceMemberDto) {
        return new ResponseEntity<>(spaceMemberService.updateSpaceMember(spaceMemberDto, user), HttpStatus.CREATED);
    }

    // TODO: Добавить пользователя в аргументы, понадобится для проверки прав
    // TODO: Заменить POST на PUT и убрать /delete_space_member/. Заменить статус код на OK
    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/delete_space_member/{id}")
    public ResponseEntity<SpaceMemberDto> deleteSpaceMember(@PathVariable Long id) {
        spaceMemberService.deleteSpaceMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
