package org.example.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkspaceDto {
    private long workspaceId;
    private String workspaceName;
    private String workspaceDescription;
    private int ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Long> membersId;
    private String settings;
}
