package org.example.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "space-service", name = "workspaces")
public class WorkspaceEntity {
    @Id
    @SequenceGenerator(name = "workspace_seq", sequenceName = "worksapce_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long workspaceId;

    @Column(nullable = false)
    private String workspaceName;

    @Column(nullable = false)
    private String workspaceDescription;

    @Column(nullable = false)
    private int ownerId;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    @ElementCollection
    @Column(nullable = false)
    private List<Long> membersId;

    @Column(nullable = false)
    private JsonNode settings;
}
