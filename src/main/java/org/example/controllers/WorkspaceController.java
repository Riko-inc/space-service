package org.example.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.example.domain.dto.WorkspaceDto;
import org.example.domain.dto.requests.WorkspaceCreateRequest;
import org.example.domain.dto.requests.WorkspaceUpdateRequest;
import org.example.services.WorkspaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/spaces")
@SecurityRequirement(name = "JWT")
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @GetMapping
    public ResponseEntity<List<WorkspaceDto>> findAllWorkspaces(@AuthenticationPrincipal UserDetails user) {
        return new ResponseEntity<>(workspaceService.findAllWorkspaces(user), HttpStatus.OK);
    }

    @GetMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceDto> findWorkspaceById(@AuthenticationPrincipal UserDetails user, @PathVariable Long workspaceId) {
        return new ResponseEntity<>(workspaceService.findWorkspaceById(workspaceId, user), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WorkspaceDto> createWorkspace(@AuthenticationPrincipal UserDetails user, @RequestBody WorkspaceCreateRequest workspace) {
        return new ResponseEntity<>(workspaceService.createWorkspace(workspace, user), HttpStatus.CREATED);
    }

    @PutMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceDto> updateWorkspace(@AuthenticationPrincipal UserDetails user, @RequestBody WorkspaceUpdateRequest workspace,  @PathVariable Long workspaceId) {
        return new ResponseEntity<>(workspaceService.updateWorkspace(workspace, user, workspaceId), HttpStatus.OK);
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<HttpStatus> deleteWorkspaceById(@AuthenticationPrincipal UserDetails user, @PathVariable Long workspaceId) {
        workspaceService.deleteWorkspaceById(workspaceId, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}