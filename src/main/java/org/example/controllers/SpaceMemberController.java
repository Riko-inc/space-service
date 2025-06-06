package org.example.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.example.domain.dto.SpaceMemberDto;
import org.example.domain.dto.requests.SpaceMemberAddRequest;
import org.example.domain.dto.requests.SpaceMemberUpdateRequest;
import org.example.services.SpaceMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/spaces/{workspaceId}/members")
@SecurityRequirement(name = "JWT")
public class SpaceMemberController {
    private final SpaceMemberService spaceMemberService;

    @GetMapping("/{memberId}")
    @PreAuthorize("@AccessService.isWorkspaceReader(#user, #workspaceId)")
    public ResponseEntity<SpaceMemberDto> getSpaceMember(@AuthenticationPrincipal UserDetails user, @PathVariable Long memberId, @PathVariable Long workspaceId) {
        return new ResponseEntity<>(spaceMemberService.findSpaceMemberById(user, workspaceId, memberId), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("@AccessService.isWorkspaceOwner(#user, #workspaceId)")
    public ResponseEntity<SpaceMemberDto> addSpaceMember(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceMemberAddRequest request, @PathVariable Long workspaceId) {
        return new ResponseEntity<>(spaceMemberService.addSpaceMember(user, request, workspaceId), HttpStatus.CREATED);
    }

    @PutMapping("/{memberId}")
    @PreAuthorize("@AccessService.isWorkspaceOwner(#user, #workspaceId)")
    public ResponseEntity<SpaceMemberDto> updateSpaceMember(@AuthenticationPrincipal UserDetails user, @RequestBody SpaceMemberUpdateRequest request, @PathVariable Long memberId, @PathVariable Long workspaceId) {
        return new ResponseEntity<>(spaceMemberService.updateSpaceMember(user, request, memberId, workspaceId), HttpStatus.OK);
    }

    @DeleteMapping("/{memberId}")
    @PreAuthorize("@AccessService.isWorkspaceOwner(#user, #workspaceId)")
    public ResponseEntity<HttpStatus> deleteSpaceMember(@AuthenticationPrincipal UserDetails user, @PathVariable Long memberId, @PathVariable Long workspaceId) {
        spaceMemberService.deleteSpaceMember(user, memberId, workspaceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
