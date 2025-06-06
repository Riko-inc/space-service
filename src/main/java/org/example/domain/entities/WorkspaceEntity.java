package org.example.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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
    @Column(name = "workspace_id")
    private Long workspaceId;

    @Column(name = "workspace_name", nullable = false)
    private String workspaceName;

    @Column(name = "workspace_description")
    private String workspaceDescription;

    @Column(name = "task_prefix", nullable = false, updatable = false)
    private String taskPrefix;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "space_member_id", nullable = false)
//    private SpaceMemberEntity owner;

    @Column(name = "created_date_time", nullable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @Builder.Default
    private LocalDateTime createdDateTime = LocalDateTime.now();

    @Column(name = "updated_date_time", nullable = false)
    @LastModifiedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @Builder.Default
    private LocalDateTime updatedDateTime  = LocalDateTime.now();

    @OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SpaceMemberEntity> members;

    @OneToOne(mappedBy = "workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private SpaceSettingsEntity settings;
}
