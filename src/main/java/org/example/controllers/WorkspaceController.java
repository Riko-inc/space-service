package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.domain.dto.WorkspaceDto;
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
    @GetMapping
    public ResponseEntity<List<WorkspaceDto>> findAllWorkspaces() {
        return new ResponseEntity<>( workspaceService.findAllWorkspaces(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkspaceDto> findWorkspaceById(@PathVariable Long id) {
        return new ResponseEntity<>(workspaceService.findWorkspaceById(id), HttpStatus.OK);
    }

    @PostMapping("/save_workspace")
    public ResponseEntity<WorkspaceDto> createWorkspace(@AuthenticationPrincipal UserDetails user, @RequestBody WorkspaceDto workspace) {
        return new ResponseEntity<>(workspaceService.saveWorkspace(workspace, user), HttpStatus.CREATED);
    }

    @PostMapping("/update_workspace")
    public ResponseEntity<WorkspaceDto> updateWorkspace(@AuthenticationPrincipal UserDetails user, @RequestBody WorkspaceDto workspace) {
        return new ResponseEntity<>(workspaceService.updateWorkspace(workspace, user), HttpStatus.OK);
    }

    @DeleteMapping("/delete_workspace/{id}")
    public ResponseEntity<HttpStatus> deleteWorkspaceById(@PathVariable Long id) {
        workspaceService.deleteWorkspaceById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
