package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.domain.dto.WorkspaceDto;
import org.example.domain.entities.UserEntity;
import org.example.services.WorkspaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {
    private final WorkspaceService workspaceService;
    @GetMapping
    public ResponseEntity<List<WorkspaceDto>> findAllWorkspaces() {
        return new ResponseEntity<>( workspaceService.findAllWorkspaces(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkspaceDto> findWorkspaceById(@PathVariable long id) {
        return new ResponseEntity<>(workspaceService.findWorkspaceById(id), HttpStatus.OK);
    }

    @PostMapping("/save_workspace")
    public ResponseEntity<String> createWorkspace(@AuthenticationPrincipal UserDetails user, @RequestBody WorkspaceDto workspace) {
        workspaceService.saveWorkspace(workspace, user);
        return new ResponseEntity<>("Workspace created", HttpStatus.CREATED);
    }

    @PostMapping("/update_workspace")
    public ResponseEntity<String> updateWorkspace(@RequestBody WorkspaceDto workspace) {
        workspaceService.updateWorkspace(workspace);
        return new ResponseEntity<>("Workspace updated", HttpStatus.OK);
    }

    @DeleteMapping("/delete_workspace/{id}")
    public ResponseEntity<HttpStatus> deleteWorkspaceById(@PathVariable long id) {
        workspaceService.deleteWorkspaceById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
