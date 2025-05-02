package org.example.controllers;

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
@RequestMapping("/api/v1/space_member")
public class SpaceMemberController {
    private final SpaceMemberService spaceMemberService;

    @GetMapping("/{id}")
    public ResponseEntity<SpaceMemberDto> getSpaceMember(@PathVariable Long id) {
        return new ResponseEntity<>(spaceMemberService.findSpaceMemberById(id), HttpStatus.OK);
    }

    @PostMapping("/save_space_member")
    public ResponseEntity<SpaceMemberDto> saveSpaceMember(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceMemberDto spaceMemberDto) {
        return new ResponseEntity<>(spaceMemberService.saveSpaceMember(spaceMemberDto, user), HttpStatus.CREATED);
    }

    @PostMapping("/update_space_member")
    public ResponseEntity<SpaceMemberDto> updateSpaceMember(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceMemberDto spaceMemberDto) {
        return new ResponseEntity<>(spaceMemberService.updateSpaceMember(spaceMemberDto, user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete_space_member/{id}")
    public ResponseEntity<SpaceMemberDto> deleteSpaceMember(@PathVariable Long id) {
        spaceMemberService.deleteSpaceMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
