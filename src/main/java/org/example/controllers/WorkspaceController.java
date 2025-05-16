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
@RequestMapping("/api/v1/workspace")
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @SecurityRequirement(name = "JWT")
    @GetMapping
    public ResponseEntity<List<WorkspaceDto>> findAllWorkspaces(@AuthenticationPrincipal UserDetails user) {
        return new ResponseEntity<>(workspaceService.findAllWorkspaces(user), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @GetMapping("/{id}")
    public ResponseEntity<WorkspaceDto> findWorkspaceById(@AuthenticationPrincipal UserDetails user, @PathVariable Long id) {
        return new ResponseEntity<>(workspaceService.findWorkspaceById(id, user), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @PostMapping
    public ResponseEntity<WorkspaceDto> createWorkspace(@AuthenticationPrincipal UserDetails user, @RequestBody WorkspaceCreateRequest workspace) {
        return new ResponseEntity<>(workspaceService.createWorkspace(workspace, user), HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "JWT")
    @PutMapping("/{id}")
    public ResponseEntity<WorkspaceDto> updateWorkspace(@AuthenticationPrincipal UserDetails user, @RequestBody WorkspaceUpdateRequest workspace,  @PathVariable Long id) {
        return new ResponseEntity<>(workspaceService.updateWorkspace(workspace, user, id), HttpStatus.OK);
    }

    @SecurityRequirement(name = "JWT")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteWorkspaceById(@AuthenticationPrincipal UserDetails user, @PathVariable Long id) {
        workspaceService.deleteWorkspaceById(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}