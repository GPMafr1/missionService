package com.africom.mission.repository;

import com.africom.mission.domain.SiteMission;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SiteMission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SiteMissionRepository extends JpaRepository<SiteMission, Long> {
}
