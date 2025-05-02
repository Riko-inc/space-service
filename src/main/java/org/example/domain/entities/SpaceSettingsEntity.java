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
    @SequenceGenerator(name = "settings_seq", sequenceName = "settings_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long spaceSettingsId;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private WorkspaceEntity spaceSettings;
}
