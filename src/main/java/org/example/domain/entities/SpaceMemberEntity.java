package org.example.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @SequenceGenerator(name = "member_seq", sequenceName = "member_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.READER;

    @JsonFormat(pattern = "dd-mm-yyyy HH:MM")
    private LocalDateTime invitedDateTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private SpaceMemberEntity invitedByMember;
}
