package org.example.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class WorkspaceSettingsDto {
    private long workSpaceId;
    private String internalId;
    private Boolean publicAccess;
    private String timeZone;

}
