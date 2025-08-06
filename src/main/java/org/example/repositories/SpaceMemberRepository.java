package org.example.repositories;

import org.example.domain.entities.SpaceMemberEntity;
import org.example.domain.entities.WorkspaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpaceMemberRepository extends JpaRepository<SpaceMemberEntity, Long> {
    Optional<SpaceMemberEntity> findSpaceMemberEntityByUserIdAndWorkspace_WorkspaceId(Long userId, Long workspaceId);
    List<SpaceMemberEntity> findAllByUserIdAndWorkspace_WorkspaceId(Long userId, Long workspaceId);
}
