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
public class SpaceSettings {
    @Id
    @SequenceGenerator(name = "settings_seq", sequenceName = "settings_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long spaceSettingId;
}
