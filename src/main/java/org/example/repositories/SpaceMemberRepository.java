package org.example.repositories;

import org.example.domain.entities.SpaceMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceMemberRepository extends JpaRepository<SpaceMemberEntity, Long> {
}
