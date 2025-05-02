package org.example.repositories;

import org.example.domain.entities.SpaceSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpaceSettingsRepository extends JpaRepository<SpaceSettingsEntity, Long> {
}
