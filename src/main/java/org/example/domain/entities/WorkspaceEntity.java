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
    private String workspaceDescription; //TODO: Может быть null

    @Column(nullable = false)
    private String taskPrefix;

    @Column(nullable = false)
    private Long ownerId;

    @JsonFormat(pattern = "dd-mm-yyyy HH:MM") //TODO: Есть аннотации, которые проставляют автоматически, надо добавить
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd-mm-yyyy HH:MM") //TODO: Есть аннотации, которые проставляют автоматически, надо добавить
    private LocalDateTime updatedAt;

    // Я не шарю, чё здесь написано. Это гпт сказал так сделать. Надо будет TODO: разобраться
    @OneToMany(
            mappedBy = "workspace",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<SpaceMemberEntity> members = new ArrayList<>();

    @OneToOne(
            mappedBy = "workspace",
            cascade = CascadeType.ALL,
            optional = false,
            orphanRemoval = true)
    @JoinColumn(nullable = false)
    private SpaceSettingsEntity settings;
}
