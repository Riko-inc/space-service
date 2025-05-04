package org.example.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/** TODO:
 *  А эт зачем?
 *  @see WorkspaceSettingsDto
 */
public class SpaceSettingsDto {
    private Long spaceSettingsId;
}
