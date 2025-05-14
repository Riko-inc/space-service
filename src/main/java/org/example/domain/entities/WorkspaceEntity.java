package org.example.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

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

    private String workspaceDescription;

    @Column(nullable = false)
    private String taskPrefix;

    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdDate;

    @Column(nullable = false)
    @LastModifiedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime updatedAt;

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
