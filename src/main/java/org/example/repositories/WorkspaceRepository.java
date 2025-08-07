package org.example.repositories;
import org.example.domain.entities.WorkspaceEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkspaceRepository extends JpaRepository<WorkspaceEntity, Long> {
    @EntityGraph(attributePaths = {"members"})
    List<WorkspaceEntity> findByMembersUserId(Long membersUserId);
    boolean existsByTaskPrefix(String taskPrefix);
    Optional<WorkspaceEntity> findByTaskPrefix(String taskPrefix);
}
