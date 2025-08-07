package org.example.repositories;

import org.example.domain.entities.SpaceMemberEntity;
import org.example.domain.entities.WorkspaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpaceMemberRepository extends JpaRepository<SpaceMemberEntity, Long> {
    Optional<SpaceMemberEntity> findSpaceMemberEntityByUserIdAndWorkspace(Long userId, WorkspaceEntity workspace);
    Optional<SpaceMemberEntity> findSpaceMemberEntityBySpaceMemberIdAndWorkspace(Long spaceMemberId, WorkspaceEntity workspace);
    Optional<SpaceMemberEntity> findByUserIdAndWorkspace(Long userId, WorkspaceEntity workspace);
    List<SpaceMemberEntity> findByWorkspace(WorkspaceEntity workspace);
}
