package org.example.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Table(schema = "space_service", name = "members")
public class SpaceMemberEntity {
    @Getter
    public enum Role {
        OWNER(0), MEMBER(1), READER(2);
        private final int order;

        Role(int order) {
            this.order = order;
        }
    }

    @Id
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.READER;

    @Column(nullable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime invitedDateTime;

    @ManyToOne
    @JoinColumn()
    private SpaceMemberEntity invitedByMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", nullable = false)
    private WorkspaceEntity workspace;
}
