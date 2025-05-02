package org.example.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "space_service", name = "workspaces")
@Accessors(chain = true)
public class WorkspaceEntity {
    @Id
    @SequenceGenerator(name = "workspace_seq", sequenceName = "workspace_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long workspaceId;

    @Column(nullable = false)
    private String workspaceName;

    @Column(nullable = false)
    private String workspaceDescription;

    @Column(nullable = false)
    private String taskPrefix;

    @Column(nullable = false)
    private Long ownerId;

    @JsonFormat(pattern = "dd-mm-yyyy HH:MM")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd-mm-yyyy HH:MM")
    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.LAZY)
    @Column(nullable = false)
    @Builder.Default
    private List<SpaceMemberEntity> membersId = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private SpaceSettingsEntity settings;
}
