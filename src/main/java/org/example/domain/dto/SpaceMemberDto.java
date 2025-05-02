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
    private Long memberId;
    private SpaceMemberEntity.Role role;
    private LocalDateTime invitesDateTime;
    private SpaceMemberDto invitedByMember;

}
