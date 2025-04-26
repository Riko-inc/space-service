package org.example.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "space-service", name = "workspaces")
@Accessors(chain = true)
public class WorkspaceEntity {
    @Id
    @SequenceGenerator(name = "workspace_seq", sequenceName = "workspace_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long workspaceId;

    @Column(nullable = false)
    private String workspaceName;

    @Column(nullable = false)
    private String workspaceDescription;

    @Column(nullable = false)
    private Long ownerId;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    @ElementCollection
    @Column(nullable = false)
    private List<Long> membersId;

    //Здесь должен быть json
    @Column(nullable = false)
    private String settings;
}
