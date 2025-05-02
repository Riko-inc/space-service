package org.example.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "space_service", name = "settings")
public class SpaceSettingsEntity {
    @Id
    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private WorkspaceEntity spaceSettingId;
}
