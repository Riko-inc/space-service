package org.example.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long workspaceId;
    private String workspaceName;
    private String workspaceDescription;
    private String taskPrefix;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    @JsonIgnore
    private List<SpaceMemberDto> members;
}
