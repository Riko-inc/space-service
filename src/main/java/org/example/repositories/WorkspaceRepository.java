package org.example.repositories;
import org.example.domain.entities.WorkspaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends JpaRepository<WorkspaceEntity, Long> {
//    void deleteWorkspaceById(long id);
//    WorkspaceDto findWorkspaceById(long id);
}
