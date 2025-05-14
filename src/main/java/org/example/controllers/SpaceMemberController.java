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
@RequestMapping("/api/v1/space/member")
public class SpaceMemberController {
    private final SpaceMemberService spaceMemberService;

    @SecurityRequirement(name = "JWT")
    @GetMapping("/{id}")
    public ResponseEntity<SpaceMemberDto> getSpaceMember(@AuthenticationPrincipal UserDetails user, @PathVariable Long id) {
        return new ResponseEntity<>(spaceMemberService.findSpaceMemberById(id, user), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping
    public ResponseEntity<SpaceMemberDto> saveSpaceMember(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceMemberDto spaceMemberDto) {
        return new ResponseEntity<>(spaceMemberService.saveSpaceMember(spaceMemberDto, user), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "JWT")
    @PutMapping
    public ResponseEntity<SpaceMemberDto> updateSpaceMember(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceMemberDto spaceMemberDto) {
        return new ResponseEntity<>(spaceMemberService.updateSpaceMember(spaceMemberDto, user), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/{id}")
    public ResponseEntity<SpaceMemberDto> deleteSpaceMember(@AuthenticationPrincipal UserDetails user, @PathVariable Long id) {
        spaceMemberService.deleteSpaceMember(id, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
