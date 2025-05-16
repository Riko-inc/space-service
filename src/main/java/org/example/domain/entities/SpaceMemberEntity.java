package org.example.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    @SequenceGenerator(name = "members_seq", sequenceName = "members_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "space_member_id")
    private Long spaceMemberId;

    // Совпадает с Id пользователя в auth-service
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.READER;

    @Column(name = "invited_date_time", nullable = false, updatable = false)
    @CreatedDate
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    @Builder.Default
    private LocalDateTime invitedDateTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invited_by_member", updatable = false)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private SpaceMemberEntity invitedByMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", nullable = false)
    private WorkspaceEntity workspace;
}
