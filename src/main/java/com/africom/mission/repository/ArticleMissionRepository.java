package com.africom.mission.repository;

import com.africom.mission.domain.ArticleMission;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ArticleMission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArticleMissionRepository extends JpaRepository<ArticleMission, Long> {
}
