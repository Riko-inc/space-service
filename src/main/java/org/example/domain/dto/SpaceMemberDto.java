package org.example.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.entities.SpaceMemberEntity;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpaceMemberDto {
    private Long memberId;                  // Совпадает с userId из auth-service
    private SpaceMemberEntity.Role role;
    private LocalDateTime invitedDateTime;
    private SpaceMemberDto invitedByMember;

}
